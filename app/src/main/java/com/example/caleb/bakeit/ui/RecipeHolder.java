package com.example.caleb.bakeit.ui;

import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.caleb.bakeit.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ViewHolder for RecipeCards
 */

public class RecipeHolder extends RecyclerView.ViewHolder {

    @Nullable
    @BindView(R.id.recipe_card_view) CardView mCardView;

    @Nullable
    @BindView(R.id.card_title_view) TextView mTitleTextView;

    public RecipeHolder(View view) {
        super(view);

        try {
            ButterKnife.bind(this, view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CardView getCardView() {
        return mCardView;
    }

    public TextView getTitleTextView() {
        return mTitleTextView;
    }

}
