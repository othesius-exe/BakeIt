package com.example.caleb.bakeit.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.example.caleb.bakeit.R;
import com.example.caleb.bakeit.Recipe;
import com.example.caleb.bakeit.RecipeDirections;
import com.example.caleb.bakeit.RecipeIngredients;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 *
 */

public class DirectionsActivity extends FragmentActivity {

    private String LOG_TAG = DirectionsActivity.class.getSimpleName();

    // ViewPagerAdapter
    private RecipeInfoPagerAdapter mRecipeInfoPagerAdapter;

    // FragmentManager
    private FragmentManager mSupportFragmentManager;

    private ArrayList<RecipeDirections> mRecipeDirections;
    private ArrayList<RecipeIngredients> mRecipeIngredients;
    private ArrayList<Object> mDirectionsObjects;
    private ArrayList<Object> mIngredientObjects;
    private Recipe mRecipe;

    private RecipeAdapter mDirectionsAdapter;
    private RecipeAdapter mIngredientsAdapter;
    private LinearLayoutManager mLayoutManager;

    private Bundle mBundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directions);
        ButterKnife.bind(this);
        mSupportFragmentManager = getSupportFragmentManager();
        mBundle = new Bundle();

        // Get the starting intent
        Intent intent = getIntent();
        // Create a Recipe Object from the extras that were shipped with the intent
        mRecipe = intent.getParcelableExtra("recipe");

        if (mRecipe != null) {
            // Get the name of the recipe, so we can populate the title page
            // Draw the ArrayLists of Directions and Ingredients out
            mRecipeDirections = mRecipe.getDirections();
            mRecipeIngredients = mRecipe.getIngredients();
        }

        // Check for a savedInstance
        // Otherwise create a new instance
        if (savedInstanceState == null) {
            DirectionsFragment directionsFragment = new DirectionsFragment();

            directionsFragment.setArguments(intent.getExtras());
            mSupportFragmentManager.beginTransaction()
                    .add(R.id.view_pager, directionsFragment)
                    .commit();

            IngredientFragment ingredientFragment = new IngredientFragment();
            mSupportFragmentManager.beginTransaction()
                    .add(R.id.view_pager, ingredientFragment)
                    .commit();
        }


        // Prepare our LayoutManager
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // The Adapter accepts a list of generic objects
        // Instantiate New object lists
        mDirectionsObjects = new ArrayList<>();
        mIngredientObjects = new ArrayList<>();

        // Add the ArrayLists to the Object ArrayLists
        mDirectionsObjects.add(mRecipeDirections);
        mIngredientObjects.add(mRecipeIngredients);

        // Set up our Adapter, passing in the objects Lists
        mDirectionsAdapter = new RecipeAdapter(this, mDirectionsObjects);
        mIngredientsAdapter = new RecipeAdapter(this, mIngredientObjects);

        mBundle.putParcelableArrayList("ingredients", mRecipeIngredients);
        mBundle.putParcelableArrayList("directions", mRecipeDirections);
        mRecipeInfoPagerAdapter = new RecipeInfoPagerAdapter(this, mBundle, mSupportFragmentManager);
        Log.i(LOG_TAG, "Bundle: " + mBundle.toString());
    }
}
