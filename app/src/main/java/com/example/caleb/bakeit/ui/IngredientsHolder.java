package com.example.caleb.bakeit.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.caleb.bakeit.R;

import butterknife.BindView;

/**
 * ViewHolder for Ingredients list
 */

public class IngredientsHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.recipe_title) TextView mTitleView;
    @BindView(R.id.ingredient_name) TextView mIngredientNameView;
    @BindView(R.id.ingredient_measurement) TextView mIngredientMeasurementView;
    @BindView(R.id.ingredient_quantity) TextView mIngredientQuantityView;

    IngredientsHolder(View view) {
        super(view);
    }

    public TextView getTitleView() {
        return mTitleView;
    }

    public TextView getIngredientview() {
        return mIngredientNameView;
    }

    public TextView getIngredientMeasurementView() {
        return mIngredientMeasurementView;
    }

    public TextView getIngredientQuantityView() {
        return mIngredientQuantityView;
    }

    public void setTitleView(TextView titleView) {
        mTitleView = titleView;
    }

    public void setIngredientNameView(TextView nameView) {
        mIngredientNameView = nameView;
    }

    public void setIngredientMeasurementView(TextView measurementView) {
        mIngredientMeasurementView = measurementView;
    }

    public void setIngredientQuantityView(TextView quantityView) {
        mIngredientQuantityView = quantityView;
    }
}
