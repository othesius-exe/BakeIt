package com.example.caleb.bakeit;

/**
 * Recipe Ingredients Object
 */

public class RecipeIngredients {

    private String mIngredient;
    private int mQuantity;
    private String mMeasurement;

    public RecipeIngredients(String ingredient, String measurement, int quantity) {
        mIngredient = ingredient;
        mMeasurement = measurement;
        mQuantity = quantity;
    }

    public String getIngredient() {
        return mIngredient;
    }

    public String getMeasurement() {
        return mMeasurement;
    }

    public int getQuantity() {
        return mQuantity;
    }
}
