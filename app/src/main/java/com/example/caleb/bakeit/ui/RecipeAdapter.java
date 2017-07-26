package com.example.caleb.bakeit.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.caleb.bakeit.R;
import com.example.caleb.bakeit.Recipe;
import com.example.caleb.bakeit.RecipeDirections;
import com.example.caleb.bakeit.RecipeIngredients;

import java.util.ArrayList;

/**
 * Adapter for Recipe, RecipeDirections and RecipeIngredients Recycler
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Object> mObjects;
    private final int DIRECTIONS = 0, INGREDIENTS = 1, RECIPE = 2;
    private Context mContext;
    private Bundle mBundle;

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
                View directionsHolder = inflater.inflate(R.layout.directions_fragment, parent, false);
                viewHolder = new DirectionsHolder(directionsHolder);
                break;
            case INGREDIENTS:
                // Create a holder for Ingredients
                View ingredientsHolder = inflater.inflate(R.layout.ingredients_fragment, parent, false);
                viewHolder = new IngredientsHolder(ingredientsHolder);
                break;
            case RECIPE:
                View recipeHolder = inflater.inflate(R.layout.recipe_card_layout, parent, false);
                viewHolder = new RecipeHolder(recipeHolder);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case DIRECTIONS:
                String content = "";
                DirectionsHolder directionsHolder = (DirectionsHolder) holder;
                RecipeDirections recipeDirections = (RecipeDirections) mObjects.get(position);
                for (int d = 0; d < mObjects.size(); d++) {
                    TextView stepView = ((DirectionsHolder) holder).getStepView();
                    TextView contentView = ((DirectionsHolder) holder).getDirectionView();
                    if (recipeDirections.getStepContent().equals("")) {
                        content = mContext.getResources().getString(R.string.error);
                    } else {
                        content = recipeDirections.getStepContent();
                    }
                    stepView.setText(recipeDirections.getStepNumber());
                    contentView.setText(content);
                    configureDirectionsHolder(directionsHolder, position);
                }
                break;
            case INGREDIENTS:
                IngredientsHolder ingredientsHolder = (IngredientsHolder) holder;
                RecipeIngredients recipeIngredients = (RecipeIngredients) mObjects.get(position);
                for (int i = 0; i < mObjects.size(); i++) {
                    TextView ingredientView = ((IngredientsHolder) holder).getIngredientview();
                    TextView quantityView = ((IngredientsHolder) holder).getIngredientQuantityView();
                    TextView measurementView = ((IngredientsHolder) holder).getIngredientMeasurementView();

                    ingredientView.setText(recipeIngredients.getIngredient());
                    quantityView.setText(recipeIngredients.getQuantity());
                    measurementView.setText(recipeIngredients.getMeasurement());

                    configureIngredientsHolder(ingredientsHolder, position);
                }
                break;
            case RECIPE:
                RecipeHolder recipeHolder = (RecipeHolder) holder;
                mBundle = new Bundle();
                final Recipe recipe = (Recipe) mObjects.get(position);
                for (int j = 0; j < mObjects.size(); j++) {
                    TextView titleView = ((RecipeHolder) holder).getTitleTextView();
                    titleView.setText(recipe.getTitle());
                    configureRecipeHolder(recipeHolder, position);
                    recipeHolder.getCardView().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent directionsIntent = new Intent(mContext, DirectionsActivity.class);
                            mBundle.putParcelable("recipe", recipe);
                            directionsIntent.putExtras(mBundle);
                            mContext.startActivity(directionsIntent);
                        }
                    });
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
        } else if (mObjects.get(position) instanceof Recipe) {
            return RECIPE;
        }
        return -1;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
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

    private void configureRecipeHolder(RecipeHolder holder, int position) {
        Recipe recipe = (Recipe) mObjects.get(position);
        if (recipe != null) {
            holder.getTitleTextView();
            holder.getCardView();
        }
    }
}
