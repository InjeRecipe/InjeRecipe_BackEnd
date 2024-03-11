package com.example.injerecipe.service;

import com.example.injerecipe.dto.request.RecipeSearchRequest;
import com.example.injerecipe.dto.response.RecipeSearchResponse;
import com.example.injerecipe.entity.Recipe;
import com.example.injerecipe.repository.RecipeRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
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
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeService {
    @Value("${openApi.recipe.serviceKey}")
    private String recipeServiceKey;

    @Value("${openApi.dataType}")
    private String dataType;

    private final RecipeRepository recipeRepository;


    public List<RecipeSearchResponse> searchRecipes(RecipeSearchRequest request) {
        List<Recipe> recipeList = recipeRepository.findByRecipeNmContaining(request.getKeyword1());
        for(int i = 0; i<recipeRepository.findByRecipeNmContaining(request.getKeyword2()).size(); i++)
            recipeList.add(recipeRepository.findByRecipeNmContaining(request.getKeyword2()).get(i));
        for(int i = 0; i<recipeRepository.findByRecipeNmContaining(request.getKeyword3()).size(); i++)
            recipeList.add(recipeRepository.findByRecipeNmContaining(request.getKeyword3()).get(i));
        for(int i = 0; i<recipeRepository.findByRecipeNmContaining(request.getKeyword4()).size(); i++)
            recipeList.add(recipeRepository.findByRecipeNmContaining(request.getKeyword4()).get(i));
        for(int i = 0; i<recipeRepository.findByRecipeNmContaining(request.getKeyword5()).size(); i++)
            recipeList.add(recipeRepository.findByRecipeNmContaining(request.getKeyword5()).get(i));
        for(int i = 0; i<recipeRepository.findByRecipeNmContaining(request.getKeyword6()).size(); i++)
            recipeList.add(recipeRepository.findByRecipeNmContaining(request.getKeyword6()).get(i));
        for(int i = 0; i<recipeRepository.findByRecipeNmContaining(request.getKeyword7()).size(); i++)
            recipeList.add(recipeRepository.findByRecipeNmContaining(request.getKeyword7()).get(i));
        for(int i = 0; i<recipeRepository.findByRecipeNmContaining(request.getKeyword8()).size(); i++)
            recipeList.add(recipeRepository.findByRecipeNmContaining(request.getKeyword8()).get(i));
        List<RecipeSearchResponse> searchResponses = new ArrayList<>();
        for(Recipe recipe : recipeList){
            searchResponses.add(RecipeSearchResponse.from(recipe));
        }

        return searchResponses;
    }

    @Transactional
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
        Recipe recipe = Recipe.builder()
                .recipeSeq((String) item.get("RCP_SEQ"))
                .recipeNm((String) item.get("RCP_NM"))
                .recipeWay((String) item.get("RCP_WAY2"))
                .recipePat((String) item.get("RCP_PAT2"))
                .recipeEng((String) item.get("INFO_ENG"))
                .recipeFileS((String) item.get("ATT_FILE_NO_MAIN"))
                .recipePartsDtls((String) item.get("RCP_PARTS_DTLS"))
                .recipeImage1((String) item.get("MANUAL_IMG01"))
                .recipeManual1((String) item.get("MANUAL01"))
                .recipeImage2((String) item.get("MANUAL_IMG02"))
                .recipeManual2((String) item.get("MANUAL02"))
                .recipeImage3((String) item.get("MANUAL_IMG03"))
                .recipeManual3((String) item.get("MANUAL03"))
                .recipeImage4((String) item.get("MANUAL_IMG04"))
                .recipeManual4((String) item.get("MANUAL04"))
                .recipeImage5((String) item.get("MANUAL_IMG05"))
                .recipeManual5((String) item.get("MANUAL05"))
                .recipeImage6((String) item.get("MANUAL_IMG06"))
                .recipeManual6((String) item.get("MANUAL06"))
                .recipeImage7((String) item.get("MANUAL_IMG07"))
                .recipeManual7((String) item.get("MANUAL07"))
                .recipeImage8((String) item.get("MANUAL_IMG08"))
                .recipeManual8((String) item.get("MANUAL08"))
                .recipeImage9((String) item.get("MANUAL_IMG09"))
                .recipeManual9((String) item.get("MANUAL09"))
                .recipeImage10((String) item.get("MANUAL_IMG10"))
                .recipeManual10((String) item.get("MANUAL10"))
                .recipeImage11((String) item.get("MANUAL_IMG11"))
                .recipeManual11((String) item.get("MANUAL11"))
                .recipeImage12((String) item.get("MANUAL_IMG12"))
                .recipeManual12((String) item.get("MANUAL12"))
                .recipeImage13((String) item.get("MANUAL_IMG13"))
                .recipeManual13((String) item.get("MANUAL13"))
                .recipeImage14((String) item.get("MANUAL_IMG14"))
                .recipeManual14((String) item.get("MANUAL14"))
                .recipeImage15((String) item.get("MANUAL_IMG15"))
                .recipeManual15((String) item.get("MANUAL15"))
                .recipeImage16((String) item.get("MANUAL_IMG16"))
                .recipeManual16((String) item.get("MANUAL16"))
                .recipeImage17((String) item.get("MANUAL_IMG17"))
                .recipeManual17((String) item.get("MANUAL17"))
                .recipeImage18((String) item.get("MANUAL_IMG18"))
                .recipeManual18((String) item.get("MANUAL18"))
                .recipeImage19((String) item.get("MANUAL_IMG19"))
                .recipeManual19((String) item.get("MANUAL19"))
                .recipeImage20((String) item.get("MANUAL_IMG20"))
                .recipeManual20((String) item.get("MANUAL20"))

                .build();
        return recipe;
    }
}
