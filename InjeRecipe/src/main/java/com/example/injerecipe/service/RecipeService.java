package com.example.injerecipe.service;

import com.example.injerecipe.dto.request.RecipeSearchRequest;
import com.example.injerecipe.dto.response.RecipeSearchResponse;
import com.example.injerecipe.entity.Recipe;
import com.example.injerecipe.repository.RecipeRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RecipeService {
    @Value("${openApi.recipe.serviceKey}")
    private String recipeServiceKey;

    @Value("${openApi.dataType}")
    private String dataType;

    private final AmazonS3Service amazonS3Service;

    private final RecipeRepository recipeRepository;


    public List<RecipeSearchResponse> searchRecipes(RecipeSearchRequest request) {
        List<RecipeSearchResponse> searchResponses = new ArrayList<>();
        List<String> keywords = request.getKeywords();

        for (String keyword : keywords) {
            if (keyword != null) {
                List<Recipe> recipes = recipeRepository.findByRecipeNmContainingKeyword(keyword);
                System.out.println(recipes);
                if (!recipes.isEmpty()) {
                    searchResponses.add(RecipeSearchResponse.from(recipes.get(0)));
                } else {

                    searchResponses.add(RecipeSearchResponse.builder()
                            .recipeNm(keyword)
                            .errorMessage("해당 레시피를 추가해주세요.")
                            .build());
                }
            }
        }
        return searchResponses;
    }


    public List<RecipeSearchResponse> searchRecipe(RecipeSearchRequest request) {
        List<Recipe> recipeList = recipeRepository.findByRecipeNmContainingKeyword(request.getKeywords().get(0));
        List<RecipeSearchResponse> searchResponses = new ArrayList<>();

        if(!recipeList.isEmpty()) {
            for (Recipe recipe : recipeList) {
                searchResponses.add(RecipeSearchResponse.from(recipe));
            }
        }else{

            searchResponses.add(RecipeSearchResponse.builder()
                    .recipeNm(request.getKeywords().get(0))
                    .errorMessage("해당 레시피를 추가해주세요.")
                    .build());
        }
        return searchResponses;
    }

    public String getRecipe(int start, int end) throws ParseException {
        HttpURLConnection urlConnection = null;
        InputStream stream = null;
        String result = null;

        String urlStr = "http://openapi.foodsafetykorea.go.kr/api" +
                "/" + recipeServiceKey +
                "/COOKRCP01" +
                "/" + dataType +
                "/" + start +
                "/" + end;

        try {
            URL url = new URL(urlStr);

            urlConnection = (HttpURLConnection) url.openConnection();
            stream = getNetworkConnection(urlConnection);
            result = readStreamToString(stream);

            if (stream != null) stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(result);
        JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
        JSONObject jsonCook = (JSONObject) jsonObject.get("COOKRCP01");
        JSONArray jsonArray = (JSONArray) jsonCook.get("row");
        for(int i = 0; i<jsonArray.size(); i++) {
            JSONObject jsonRow = (JSONObject) jsonArray.get(i);
            recipeRepository.save(makeDto(jsonRow));
        }
        return result;
    }

    private InputStream getNetworkConnection(HttpURLConnection urlConnection) throws IOException {
        urlConnection.setConnectTimeout(3000);
        urlConnection.setReadTimeout(3000);
        urlConnection.setRequestMethod("GET");
        urlConnection.setDoInput(true);

        if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new IOException("HTTP error code : " + urlConnection.getResponseCode());
        }

        return urlConnection.getInputStream();
    }

    private String readStreamToString(InputStream stream) throws IOException {
        StringBuilder result = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));

        String readLine;
        while ((readLine = br.readLine()) != null) {
            result.append(readLine + "\n\r");
        }

        br.close();

        return result.toString();
    }

    private Recipe makeDto(JSONObject item) {
        Recipe.RecipeBuilder recipeBuilder = Recipe.builder()
                .recipeSeq(getString(item, "RCP_SEQ"))
                .recipeNm(getString(item, "RCP_NM"))
                .recipeWay(getString(item, "RCP_WAY2"))
                .recipePat(getString(item, "RCP_PAT2"))
                .recipeEng(getString(item, "INFO_ENG"))
                .recipeFileS(getString(item, "ATT_FILE_NO_MAIN"))
                .recipePartsDtls(getString(item, "RCP_PARTS_DTLS"));

        List<String> recipeImages = new ArrayList<>();
        List<String> recipeManuals = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {
            String imageKey = "MANUAL_IMG" + String.format("%02d", i);
            String manualKey = "MANUAL" + String.format("%02d", i);
            recipeImages.add(getString(item, imageKey));
            recipeManuals.add(getString(item, manualKey));
        }

        recipeBuilder
                .recipeImages(recipeImages)
                .recipeManuals(recipeManuals);

        return recipeBuilder.build();
    }

    private String getString(JSONObject item, String key) {
        return (String) item.get(key);
    }
}
