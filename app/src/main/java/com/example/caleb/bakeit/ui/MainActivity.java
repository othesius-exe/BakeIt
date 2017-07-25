package com.example.caleb.bakeit.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.caleb.bakeit.R;
import com.example.caleb.bakeit.Recipe;
import com.example.caleb.bakeit.RecipeDirections;
import com.example.caleb.bakeit.RecipeIngredients;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    // Log Tag
    private String LOG_TAG = MainActivity.class.getSimpleName();

    // Objects Fetched from Json
    private Recipe mRecipe;
    private RecipeDirections mRecipeDirections;
    private RecipeIngredients mRecipeIngredients;

    // ArrayLists of each object type to be passed to RecyclerView.Adapter
    private ArrayList<Object> mRecipeObjectArrayList;
    private ArrayList<Object> mDirectionsObjectArrayList;
    private ArrayList<Object> mIngredientsObjectArrayList;
    private ArrayList<Recipe> mRecipeArrayList;
    private ArrayList<RecipeDirections> mRecipeDirectionsArrayList;
    private ArrayList<RecipeIngredients> mRecipeIngredientsArrayList;

    // Adapter for Cards
    private RecipeAdapter mRecipeCardAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    // Views to populate
    @Nullable
    @BindView(R.id.card_recycler_view) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assert mRecyclerView != null;
        try {
            ButterKnife.bind(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Try to parse the JSON Asset to create Objects
        mRecipeObjectArrayList = new ArrayList<>();
        mDirectionsObjectArrayList = new ArrayList<>();
        mIngredientsObjectArrayList = new ArrayList<>();
        mRecipeArrayList = new ArrayList<>();
        mRecipeDirectionsArrayList = new ArrayList<>();
        mRecipeIngredientsArrayList = new ArrayList<>();

        // Recipe variables
        String name = "";

        // Ingredients Variables
        int quantity = 0;
        String measurement = "";
        String ingredient = "";

        // Directions Variables
        int stepNumber = 0;
        String content = "";
        String videoUrl = "";

        try {
            // Base JSON Response
            JSONArray jsonArray = new JSONArray((loadJSONFromAsset()));
            for (int x = 0; x < jsonArray.length(); x++) {
                JSONObject jsonObject = jsonArray.getJSONObject(x);
                // Iterate over the Base Response
                for (int i = 0; i < jsonObject.length(); i++) {
                    // Get the name of the Recipe
                    name = jsonObject.getString("name");

                    // Get an Array of Ingredients, Measurement types and Measurements
                    JSONArray ingredientArray = jsonObject.getJSONArray("ingredients");
                    // Iterate over each Array and get the objects inside
                    for (int j = 0; j < ingredientArray.length(); j++) {
                        // For each ingredient Object, set the correct values
                        JSONObject thisIngredient = ingredientArray.getJSONObject(j);
                        quantity = thisIngredient.getInt("quantity");
                        measurement = thisIngredient.getString("measure");
                        ingredient = thisIngredient.getString("ingredient");

                        // Create a new RecipeIngredients Object
                        // Add that object to the Ingredients ArrayList
                        mRecipeIngredients = new RecipeIngredients(ingredient, measurement, quantity);
                        mIngredientsObjectArrayList.add(mRecipeIngredients);
                        mRecipeIngredientsArrayList.add(mRecipeIngredients);
                    }
                    // Get an Array of Directions
                    JSONArray directionsArray = jsonObject.getJSONArray("steps");
                    for (int s = 0; s < directionsArray.length(); s++) {
                        // For each direction, set the values
                        JSONObject thisDirection = directionsArray.getJSONObject(s);
                        stepNumber = thisDirection.getInt("id");
                        content = thisDirection.getString("description");
                        if (thisDirection.has("videoURL")) {
                            videoUrl = thisDirection.getString("videoURL");
                        }
                        mRecipeDirections = new RecipeDirections(stepNumber, content, videoUrl);
                        mDirectionsObjectArrayList.add(mRecipeDirections);
                        mRecipeDirectionsArrayList.add(mRecipeDirections);
                    }

                    // Now, create a new complete RecipeObject
                    mRecipe = new Recipe(name, mRecipeDirectionsArrayList, mRecipeIngredientsArrayList);
                    mRecipeArrayList.add(mRecipe);
                    mRecipeObjectArrayList.add(mRecipe);

                    mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    mRecyclerView.setHasFixedSize(true);

                    mRecipeCardAdapter = new RecipeAdapter(this, mRecipeObjectArrayList);
                    mRecyclerView.setAdapter(mRecipeCardAdapter);

                    Log.i(LOG_TAG, "" + mRecyclerView.getChildCount());
                    Log.i(LOG_TAG, mRecipe.getTitle());
                    Log.i(LOG_TAG, "" + mRecipeCardAdapter.getItemCount());
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Load the JSON Asset
    public String loadJSONFromAsset() {
        String json = null;

        try {
            InputStream inputStream = getAssets().open("recipe.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }
}
