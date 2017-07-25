package com.example.caleb.bakeit.Utils;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.caleb.bakeit.Recipe;

import java.util.ArrayList;

/**
 *
 */

public class RecipeLoader extends AsyncTaskLoader<ArrayList<Recipe>> {

    private String LOG_TAG = RecipeLoader.class.getSimpleName();

    private String mURL;

    public RecipeLoader(Context context, String url) {
        super(context);
        Log.v(LOG_TAG, "Url in loader: " + url);
        mURL = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
        Log.i(LOG_TAG, "Retrieving Recipes...");
    }

    @Override
    public ArrayList<Recipe> loadInBackground() {
        if (mURL == null) {
            return null;
        }
        ArrayList<Recipe> recipes;
        recipes = QueryUtils.fetchRecipeData(mURL);
        return recipes;
    }
}