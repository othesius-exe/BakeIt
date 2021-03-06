package com.example.caleb.bakeit.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.caleb.bakeit.R;
import com.example.caleb.bakeit.Recipe;
import com.example.caleb.bakeit.RecipeDirections;
import com.example.caleb.bakeit.RecipeIngredients;
import com.example.caleb.bakeit.Utils.RecipeLoader;
import com.example.caleb.bakeit.adapters.RecipeAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    // Log Tag
    private String LOG_TAG = MainActivity.class.getSimpleName();

    private static final String mRecipeUrl = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    // Objects Fetched from Json
    private Recipe mRecipe;

    // ArrayLists of each object type to be passed to RecyclerView.Adapter
    private ArrayList<Object> mRecipeObjectArrayList;
    private ArrayList<Recipe> mRecipeArrayList;
    private ArrayList<RecipeDirections> mRecipeDirectionsArrayList;
    private ArrayList<RecipeIngredients> mRecipeIngredientsArrayList;
    private Bundle mBundle;

    // Adapter for Cards
    private RecipeAdapter mRecipeCardAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    // Loader Variables
    private LoaderManager mLoaderManager;
    private int RECIPE_LOADER_ID = 0;

    // Views to populate
    @Nullable
    @BindView(R.id.card_recycler_view) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBundle = new Bundle();

        assert mRecyclerView != null;
        try {
            ButterKnife.bind(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Try to parse the JSON Asset to create Objects
        mRecipeObjectArrayList = new ArrayList<>();
        mRecipeArrayList = new ArrayList<>();
        mRecipeDirectionsArrayList = new ArrayList<>();
        mRecipeIngredientsArrayList = new ArrayList<>();

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        mRecipeCardAdapter = new RecipeAdapter(this, mRecipeObjectArrayList);
        mRecyclerView.setAdapter(mRecipeCardAdapter);

        mLoaderManager = getSupportLoaderManager();

        ConnectivityManager connectivityManager = (ConnectivityManager)
                getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if (isConnected) {
            mLoaderManager.initLoader(RECIPE_LOADER_ID, null, new RecipeCallback());
        }
    }

    private class RecipeCallback implements LoaderManager.LoaderCallbacks<ArrayList<Recipe>> {

        @Override
        public Loader<ArrayList<Recipe>> onCreateLoader(int id, Bundle args) {
            Log.i(LOG_TAG, "Creating Loader");
            return new RecipeLoader(MainActivity.this, mRecipeUrl);
        }

        @Override
        public void onLoadFinished(Loader<ArrayList<Recipe>> loader, ArrayList<Recipe> data) {
            Log.i(LOG_TAG, "Load finished");
            mRecipeArrayList.clear();
            mRecipeObjectArrayList.clear();
            mRecyclerView.setAdapter(mRecipeCardAdapter);
            if (data != null && !data.isEmpty()) {
                mRecipeArrayList.addAll(data);
                mRecipeObjectArrayList.addAll(data);
                mBundle.putParcelableArrayList("directions", mRecipeDirectionsArrayList);
                mBundle.putParcelableArrayList("ingredients", mRecipeIngredientsArrayList);
            }
        }

        @Override
        public void onLoaderReset(Loader<ArrayList<Recipe>> loader) {
            mRecipeArrayList.clear();
            mRecipeObjectArrayList.clear();
            mLoaderManager.initLoader(RECIPE_LOADER_ID, null, this);
        }
    }
}
