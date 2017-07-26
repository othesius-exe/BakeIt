package com.example.caleb.bakeit.Utils;

import android.text.TextUtils;
import android.util.Log;

import com.example.caleb.bakeit.Recipe;
import com.example.caleb.bakeit.RecipeDirections;
import com.example.caleb.bakeit.RecipeIngredients;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 *
 */

public class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    // Query the Recipe URL
    public static ArrayList<Recipe> fetchRecipeData(String requestUrl) {
        // Create a URL Object
        URL url = createUrl(requestUrl);

        String jsonResponse = null;

        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "Error closing input stream");
        }

        // Extract relevant fields from json and create a Recipe Object
        ArrayList<Recipe> recipes = extractRecipeFromJson(jsonResponse);

        // Return Recipes
        return recipes;
    }

    // Build the Url
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "Bad Url", e);
        }
        return url;
    }

    // Make an Http Request to the Url
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // Check for an empty URL
        if (url == null) {
            return jsonResponse;
        }

        // Try to open a connection otherwise
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the connection is successful (Response code == 200)
            // Read the stream and parse the response
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Failed to connect: Response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "Problem retrieving JSON from " + url);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return jsonResponse;
    }

    // Read from the InputStream
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();

            while (line != null) {
                output.append(line);
                line = bufferedReader.readLine();
            }
        }

        return output.toString();
    }

    // Build a Recipe Object
    public static ArrayList<Recipe> extractRecipeFromJson(String recipeJson) {
        // Recipe variables
        String name = "";

        // Ingredients Variables
        double quantity = 0.0;
        String measurement = "";
        String ingredient = "";

        // Directions Variables
        int stepNumber = 0;
        String content = "";
        String videoUrl = "";
        String stepDescription = "";

        if (TextUtils.isEmpty(recipeJson)) {
            return null;
        }

        ArrayList<Recipe> recipeArrayList = new ArrayList<>();

        try {
            // Base JSON Response
            JSONArray jsonArray = new JSONArray(recipeJson);
            for (int i = 0; i < jsonArray.length(); i++) {

                ArrayList<RecipeDirections> directionsArrayList = new ArrayList<>();
                ArrayList<RecipeIngredients> ingredientsArrayList = new ArrayList<>();

                JSONObject thisRecipe = jsonArray.getJSONObject(i);
                name = thisRecipe.getString("name");
                JSONArray ingredientsArray = thisRecipe.getJSONArray("ingredients");
                for (int j = 0; j < ingredientsArray.length(); j++) {
                    JSONObject thisIngredient = ingredientsArray.getJSONObject(j);
                    quantity = thisIngredient.getDouble("quantity");
                    measurement = thisIngredient.getString("measure");
                    ingredient = thisIngredient.getString("ingredient");

                    RecipeIngredients mIngredients = new RecipeIngredients(ingredient, measurement, quantity);
                    ingredientsArrayList.add(mIngredients);
                }

                JSONArray directionsArray = thisRecipe.getJSONArray("steps");
                for (int x = 0; x < directionsArray.length(); x++) {
                    JSONObject thisStep = directionsArray.getJSONObject(x);
                    stepNumber = thisStep.getInt("id");
                    content = thisStep.getString("description");
                    videoUrl = thisStep.getString("videoURL");
                    stepDescription = thisStep.getString("shortDescription");

                    RecipeDirections mDirections = new RecipeDirections(stepNumber, stepDescription, content, videoUrl);
                    directionsArrayList.add(mDirections);
                }

                Recipe recipe = new Recipe(name, directionsArrayList, ingredientsArrayList);
                recipeArrayList.add(recipe);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "Trouble parsing JSON.");
        }
        return  recipeArrayList;
    }
}
