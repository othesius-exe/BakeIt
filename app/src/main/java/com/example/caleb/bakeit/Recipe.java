package com.example.caleb.bakeit;

import java.util.ArrayList;

/**
 *
 */

public class Recipe {

    private ArrayList<RecipeDirections> mDirections;
    private ArrayList<RecipeIngredients> mIngredients;
    private String mTitle;

    public Recipe(String title, ArrayList<RecipeDirections> directions, ArrayList<RecipeIngredients> ingredients) {
        mTitle = title;
        mDirections = directions;
        mIngredients = ingredients;
    }

    public ArrayList<RecipeDirections> getDirections() {
        return mDirections;
    }

    public ArrayList<RecipeIngredients> getIngredients() {
        return mIngredients;
    }

    public String getTitle() {
        return mTitle;
    }
}
