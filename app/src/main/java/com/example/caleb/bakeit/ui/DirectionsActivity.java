package com.example.caleb.bakeit.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.example.caleb.bakeit.R;
import com.example.caleb.bakeit.Recipe;
import com.example.caleb.bakeit.RecipeDirections;
import com.example.caleb.bakeit.RecipeIngredients;

import java.util.ArrayList;

import butterknife.BindView;
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

    // Objects
    private Recipe mRecipe;
    private ArrayList<RecipeDirections> mRecipeDirectionsArrayList;
    private ArrayList<RecipeIngredients> mRecipeIngredientsArrayList;

    // Bundles
    private Bundle mBundle;

    @BindView(R.id.view_pager) ViewPager mViewPager;

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
            mRecipeDirectionsArrayList = mRecipe.getDirections();
            mRecipeIngredientsArrayList = mRecipe.getIngredients();
        }

        mBundle.putParcelableArrayList("directions", mRecipeDirectionsArrayList);
        mBundle.putParcelableArrayList("ingredients", mRecipeIngredientsArrayList);

        // Check for a savedInstance
        // Otherwise create a new instance
        if (savedInstanceState == null) {
            DirectionsFragment directionsFragment = new DirectionsFragment();

            directionsFragment.setArguments(mBundle);
            mSupportFragmentManager.beginTransaction()
                    .add(R.id.view_pager, directionsFragment)
                    .commit();

            IngredientFragment ingredientFragment = new IngredientFragment();
            ingredientFragment.setArguments(mBundle);
            mSupportFragmentManager.beginTransaction()
                    .add(R.id.view_pager, ingredientFragment)
                    .commit();
        }

        mRecipeInfoPagerAdapter = new RecipeInfoPagerAdapter(this, mBundle, mSupportFragmentManager);
        mViewPager.setAdapter(mRecipeInfoPagerAdapter);

    }
}
