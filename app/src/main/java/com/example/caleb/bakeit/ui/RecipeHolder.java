package com.example.caleb.bakeit.ui;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.caleb.bakeit.R;

import butterknife.BindView;

/**
 * ViewHolder for RecipeCards
 */

public class RecipeHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.recipe_card_view) CardView mCardView;
    @BindView(R.id.card_title_view) TextView mTitleTextView;

    public RecipeHolder(View view) {
        super(view);
    }

    public CardView getCardView() {
        return mCardView;
    }

    public TextView getTitleTextView() {
        return mTitleTextView;
    }

}
