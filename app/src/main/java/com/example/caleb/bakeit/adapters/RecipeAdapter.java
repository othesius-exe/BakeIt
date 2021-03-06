package com.example.caleb.bakeit.adapters;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.caleb.bakeit.R;
import com.example.caleb.bakeit.Recipe;
import com.example.caleb.bakeit.RecipeDirections;
import com.example.caleb.bakeit.RecipeIngredients;
import com.example.caleb.bakeit.Widget.BakeItWidget;
import com.example.caleb.bakeit.ui.DirectionsActivity;
import com.example.caleb.bakeit.ui.DirectionsFragment;
import com.example.caleb.bakeit.ui.DirectionsHolder;
import com.example.caleb.bakeit.ui.IngredientsHolder;
import com.example.caleb.bakeit.ui.RecipeHolder;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Adapter for Recipe, RecipeDirections and RecipeIngredients Recycler
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Object> mObjects;
    private final int DIRECTIONS = 0, INGREDIENTS = 1, RECIPE = 2;
    private Context mContext;
    private Bundle mBundle;
    private DirectionsFragment directionsFragment;
    private String mUrl;

    // Give the Adapter a context and a list of Objects
    public RecipeAdapter(Context context, ArrayList<Object> objects) {
        mObjects = objects;
        mContext = context;
    }

    public RecipeAdapter(Context context, DirectionsFragment fragment, ArrayList<Object> objects) {
        directionsFragment = fragment;
        mObjects = objects;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        // Find the object type that was passed
        // Create a viewholder and inflate the proper layout for that object
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
            case RECIPE:
                // Create a holder for an entire Recipe
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
                mUrl = "";
                DirectionsHolder directionsHolder = (DirectionsHolder) holder;
                final RecipeDirections recipeDirections = (RecipeDirections) mObjects.get(position);
                for (int d = 0; d < mObjects.size(); d++) {
                    TextView stepView = ((DirectionsHolder) holder).getStepView();
                    TextView contentView = ((DirectionsHolder) holder).getDirectionView();
                    ImageView imageView = ((DirectionsHolder) holder).getImageView();
                    if (recipeDirections.getStepContent().equals("")) {
                        content = mContext.getResources().getString(R.string.error);
                    } else {
                        content = recipeDirections.getStepContent();
                    }
                    if (!recipeDirections.getVideoUrl().equals("")) {
                        imageView.setVisibility(View.VISIBLE);
                        imageView.setScaleType(ImageView.ScaleType.FIT_START);
                    }
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mUrl = recipeDirections.getVideoUrl();
                            directionsFragment.setUri(mUrl);
                        }
                    });

                    stepView.setText(recipeDirections.getStepDescription());
                    contentView.setText(content);
                    configureDirectionsHolder(directionsHolder, position);
                }
                break;
            case INGREDIENTS:
                IngredientsHolder ingredientsHolder = (IngredientsHolder) holder;
                RecipeIngredients recipeIngredients = (RecipeIngredients) mObjects.get(position);
                for (int i = 0; i < mObjects.size(); i++) {
                    if (mObjects.get(i) instanceof RecipeIngredients) {
                        double quantity = recipeIngredients.getQuantity();
                        String ingredient = recipeIngredients.getIngredient();
                        String measurement = recipeIngredients.getMeasurement();

                        TextView ingredientView = ((IngredientsHolder) holder).getIngredientView();
                        TextView quantityView = ((IngredientsHolder) holder).getIngredientQuantityView();

                        ingredientView.setText(ingredient);
                        quantityView.setText(String.valueOf(quantity));
                        quantityView.append(" " + measurement);

                        configureIngredientsHolder(ingredientsHolder, position);
                    }
                }

                break;
            case RECIPE:
                RecipeHolder recipeHolder = (RecipeHolder) holder;
                mBundle = new Bundle();
                final Recipe recipe = (Recipe) mObjects.get(position);
                for (int j = 0; j < mObjects.size(); j++) {
                    ImageView titleView = ((RecipeHolder) holder).getTitleTextView();
                    if (recipe.getTitle().equals("Cheesecake")) {
                        titleView.setImageResource(R.drawable.cheesecake);
                    } else if (recipe.getTitle().equals("Yellow Cake")) {
                        titleView.setImageResource(R.drawable.yellowcake);
                    } else if (recipe.getTitle().equals("Nutella Pie")) {
                        titleView.setImageResource(R.drawable.nutella);
                    } else if (recipe.getTitle().equals("Brownies")) {
                        titleView.setImageResource(R.drawable.brownies);
                    }
                    configureRecipeHolder(recipeHolder, position);
                    AdapterView.OnClickListener mOnClickListener = new AdapterView.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Intent to open the directions activity when a recipe card is selected
                            Intent directionsIntent = new Intent(mContext, DirectionsActivity.class);
                            // Pack up the entire recipe object that was selected and put it in a bundle
                            mBundle.putParcelable("recipe", recipe);
                            // Add the bundle to the intent as an extra
                            directionsIntent.putExtras(mBundle);
                            // Start the activity
                            mContext.startActivity(directionsIntent);

                            // Write the recipe to shared preferences using a GSON structure
                            SharedPreferences mPrefs = mContext.getSharedPreferences("recipe", Context.MODE_APPEND);
                            Gson recipeGson = new Gson();
                            String json = recipeGson.toJson(recipe);

                            SharedPreferences.Editor editor = mPrefs.edit();
                            editor.putString("recipe", json);
                            editor.apply();

                            // Update the widget every time the user selects a recipe
                            Intent updateIntent = new Intent(mContext, BakeItWidget.class);
                            updateIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
                            mContext.sendBroadcast(updateIntent);
                        }
                    };
                    ((RecipeHolder) holder).getCardView().setOnClickListener(mOnClickListener);
                }
        }
    }

    @Override
    public int getItemCount() {
        return mObjects.size();
    }

    @Override
    public int getItemViewType(int position) {
        // Finds the type of object being held by the objects Array
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

    /**
     *
     *  Helper methods for finding the correct views for each type of holder
     */
    private void configureDirectionsHolder(DirectionsHolder holder, int position) {
        RecipeDirections directions = (RecipeDirections) mObjects.get(position);
        if (directions != null) {
            holder.getDirectionView();
            holder.getStepView();
            holder.getImageView();
        }
    }

    private void configureIngredientsHolder(IngredientsHolder holder, int position) {
        RecipeIngredients ingredients = (RecipeIngredients) mObjects.get(position);
        if (ingredients != null) {
            holder.getIngredientQuantityView();
            holder.getIngredientView();
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
