package com.example.caleb.bakeit.Widget;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.caleb.bakeit.R;
import com.example.caleb.bakeit.Recipe;
import com.example.caleb.bakeit.RecipeIngredients;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);

        Gson gson = new Gson();
        String json = preferences.getString("ingredients", "");

        Type type = new TypeToken<ArrayList<RecipeIngredients>>(){}.getType();
        mIngredientsArray = gson.fromJson(json, type);
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public int getCount() {
        return mIngredientsArray.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews views = new RemoteViews(mContext.getPackageName(),
                R.layout.widget_ingredient_layout);
        views.setTextViewText(R.id.widget_recipe_name, mRecipe.getTitle());
        views.setTextViewText(R.id.widget_ingredient, mIngredients.getIngredient());
        views.setTextViewText(R.id.widget_measurement, String.valueOf(mIngredients.getQuantity()) + " " + mIngredients.getMeasurement());
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
