package com.example.caleb.bakeit.Widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.caleb.bakeit.R;
import com.example.caleb.bakeit.Recipe;
import com.example.caleb.bakeit.RecipeIngredients;

/**
 *
 */

public class BakeItWidgetAdapter implements RemoteViewsService.RemoteViewsFactory {

    private Recipe mRecipe;
    private RecipeIngredients mIngredients;
    private Context mContext;

    public BakeItWidgetAdapter(Context context, Intent intent) {
        mContext = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return 0;
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

    public void setIngredientsList(RecipeIngredients ingredients) {
        mIngredients = ingredients;
    }

}
