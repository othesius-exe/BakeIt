package com.example.caleb.bakeit.ui;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.caleb.bakeit.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ViewHolder for Ingredients list
 */

public class IngredientsHolder extends RecyclerView.ViewHolder {
    @Nullable
    @BindView(R.id.recipe_title) TextView mTitleView;
    @BindView(R.id.ingredient_name) TextView mIngredientNameView;
    @BindView(R.id.ingredient_measurement) TextView mIngredientMeasurementView;
    @BindView(R.id.ingredient_quantity) TextView mIngredientQuantityView;

    IngredientsHolder(View view) {
        super(view);

        ButterKnife.bind(this, view);
    }

    public TextView getTitleView() {
        return mTitleView;
    }

    public TextView getIngredientView() {
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
