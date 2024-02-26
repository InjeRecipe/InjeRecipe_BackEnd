package com.example.injerecipe.service;

import com.amazonaws.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService {
    private String baseUrl = "http://211.237.50.150:7080/openapi";

    private String s = "나물비빔밥";
    @Value("${openApi.serviceKey}")
    private String serviceKey;

    @Value("${openApi.recipe.serviceKey}")
    private String recipeServiceKey;

    @Value("${openApi.dataType}")
    private String dataType;

    @Value("${openApi.apiUrl}")
    private String recipeUrl;

    @Value("${openApi.step.apiUrl}")
    private String stepUrl;

    @Value("${openApi.irdnt.apiUrl}")
    private String ingredientUrl;


    public String getRecipe(int start, int end, String rcpNm) {
        HttpURLConnection urlConnection = null;
        InputStream stream = null;
        String result = null;

        String urlStr = "http://openapi.foodsafetykorea.go.kr/api" +
                "/" + recipeServiceKey +
                "/COOKRCP01" +
                "/" + dataType +
                "/" + start +
                "/" + end + "/RCP_NM=" + rcpNm;

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
        return result;
    }

    public String getIngredients(String ingredient) {
        HttpURLConnection urlConnection = null;
        InputStream stream = null;
        String result = null;

        String urlStr = "https://api.spoonacular.com/food/ingredients/search/?query=" + ingredient + "&number=5&addChildren=true&metaInformation=true&apiKey=" + ingredientUrl;

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
        return result;

    }

    public String getSteps(int start, int end) {
        HttpURLConnection urlConnection = null;
        InputStream stream = null;
        String result = null;

        String urlStr = baseUrl +
                "/" + serviceKey +
                "/" + dataType +
                "/" + stepUrl +
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
        return result;
    }

    public String getRecipes(int start, int end) {
        HttpURLConnection urlConnection = null;
        InputStream stream = null;
        String result = null;

        String urlStr = baseUrl +
                "/" + serviceKey +
                "/" + dataType +
                "/" + recipeUrl +
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

}
