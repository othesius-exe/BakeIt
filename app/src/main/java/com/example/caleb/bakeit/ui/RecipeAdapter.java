package com.example.caleb.bakeit.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.caleb.bakeit.R;
import com.example.caleb.bakeit.RecipeDirections;
import com.example.caleb.bakeit.RecipeIngredients;

import java.util.ArrayList;

/**
 * Adapter for RecipeDirections Recycler
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Object> mObjects;
    private final int DIRECTIONS = 0, INGREDIENTS = 1;
    private Context mContext;

    // Give the Adapter a context and a list of Objects
    public RecipeAdapter(Context context, ArrayList<Object> objects) {
        mObjects = objects;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch(viewType) {
            case DIRECTIONS:
                // Create a holder for Directions
                View directionsHolder = inflater.inflate(R.layout.directions_layout, parent, false);
                viewHolder = new DirectionsHolder(directionsHolder);
                break;
            case INGREDIENTS:
                // Create a holder for Ingredients
                View ingredientsHolder = inflater.inflate(R.layout.ingredients_layout, parent, false);
                viewHolder = new IngredientsHolder(ingredientsHolder);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case DIRECTIONS:
                String title = "";
                String content = "";
                DirectionsHolder directionsHolder = (DirectionsHolder) holder;
                RecipeDirections recipeDirections = (RecipeDirections) mObjects.get(position);
                for (int d = 0; d < mObjects.size(); d++) {
                    TextView titleView = ((DirectionsHolder) holder).getTitleView();
                    TextView stepView = ((DirectionsHolder) holder).getStepView();
                    TextView contentView = ((DirectionsHolder) holder).getDirectionView();
                    if (recipeDirections.getStepContent().equals("")) {
                        content = mContext.getResources().getString(R.string.error);
                    } else {
                        content = recipeDirections.getStepContent();
                    }
                    stepView.setText(recipeDirections.getStepNumber());
                    contentView.setText(recipeDirections.getStepContent());
                    configureDirectionsHolder(directionsHolder, position);
                }
                break;
            case INGREDIENTS:
                IngredientsHolder ingredientsHolder = (IngredientsHolder) holder;
                RecipeIngredients recipeIngredients = (RecipeIngredients) mObjects.get(position);
                for (int i = 0; i < mObjects.size(); i++) {
                    TextView titleView = ((IngredientsHolder) holder).getTitleView();
                    TextView ingredientView = ((IngredientsHolder) holder).getIngredientview();
                    TextView quantityView = ((IngredientsHolder) holder).getIngredientQuantityView();
                    TextView measurementView = ((IngredientsHolder) holder).getIngredientMeasurementView();

                    ingredientView.setText(recipeIngredients.getIngredient());
                    quantityView.setText(recipeIngredients.getQuantity());
                    measurementView.setText(recipeIngredients.getMeasurement());

                    configureIngredientsHolder(ingredientsHolder, position);
                }
        }
    }

    @Override
    public int getItemCount() {
        return mObjects.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mObjects.get(position) instanceof RecipeDirections) {
            return DIRECTIONS;
        } else if (mObjects.get(position) instanceof RecipeIngredients) {
            return INGREDIENTS;
        }
        return -1;
    }

    private void configureDirectionsHolder(DirectionsHolder holder, int position) {
        RecipeDirections directions = (RecipeDirections) mObjects.get(position);
        if (directions != null) {
            holder.getDirectionView();
            holder.getStepView();
        }
    }

    private void configureIngredientsHolder(IngredientsHolder holder, int position) {
        RecipeIngredients ingredients = (RecipeIngredients) mObjects.get(position);
        if (ingredients != null) {
            holder.getIngredientMeasurementView();
            holder.getIngredientQuantityView();
            holder.getIngredientQuantityView();
        }
    }
}
