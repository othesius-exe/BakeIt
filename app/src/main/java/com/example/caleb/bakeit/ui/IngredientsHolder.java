package com.example.caleb.bakeit.ui;

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


    @BindView(R.id.ingredient_name) TextView mIngredientNameView;
    @BindView(R.id.ingredient_quantity) TextView mIngredientQuantityView;

    public IngredientsHolder(View view) {
        super(view);

        ButterKnife.bind(this, view);
    }

    public TextView getIngredientView() {
        return mIngredientNameView;
    }

    public TextView getIngredientQuantityView() {
        return mIngredientQuantityView;
    }

    public void setIngredientNameView(TextView nameView) {
        mIngredientNameView = nameView;
    }

    public void setIngredientQuantityView(TextView quantityView) {
        mIngredientQuantityView = quantityView;
    }
}
