package com.example.caleb.bakeit.Widget;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.caleb.bakeit.R;
import com.example.caleb.bakeit.Recipe;
import com.example.caleb.bakeit.RecipeIngredients;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 *
 */

public class BakeItWidgetAdapter implements RemoteViewsService.RemoteViewsFactory {

    private Recipe mRecipe;
    private RecipeIngredients mIngredients;
    private ArrayList<RecipeIngredients> mIngredientsArray = new ArrayList<>();
    private Context mContext;

    public BakeItWidgetAdapter(Context context, Intent intent) {
        mContext = context;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {


        SharedPreferences preferences = mContext.getSharedPreferences("recipe", Context.MODE_PRIVATE);


        Gson gson = new Gson();
        String json = preferences.getString("recipe", "");

        mRecipe = gson.fromJson(json, Recipe.class);

        if (mRecipe != null) {
            mIngredientsArray = mRecipe.getIngredients();
        }
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public int getCount() {
        if (mIngredientsArray != null && mIngredientsArray.size() != 0) {
            return mIngredientsArray.size();
        } else {
            return 0;
        }
    }

    // This is where we set the information we want to display
    // Into their proper views for the widget
    @Override
    public RemoteViews getViewAt(int position) {
        // Create a remote views object
        // Tell it to use views in the widget_ingredient_layout.xml file
        RemoteViews views = new RemoteViews(mContext.getPackageName(),
                R.layout.widget_ingredient_layout);
        if (mIngredientsArray != null) {
            mIngredients = mIngredientsArray.get(position);
            views.setTextViewText(R.id.widget_ingredient, mIngredients.getIngredient());
            views.setTextViewText(R.id.widget_measurement, String.valueOf(mIngredients.getQuantity() + " " + mIngredients.getMeasurement()));

            Intent fillInIntent = new Intent();
            views.setOnClickFillInIntent(R.id.widget_container, fillInIntent);
        }

        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

}
