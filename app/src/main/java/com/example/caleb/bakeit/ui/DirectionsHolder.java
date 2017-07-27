package com.example.caleb.bakeit.ui;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.caleb.bakeit.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ViewHolder for a RecipeDirections Object
 */

public class DirectionsHolder extends RecyclerView.ViewHolder {

    // Views in the Directions Layout
    @Nullable
    @BindView(R.id.card_title_view) TextView mTitleView;
    @BindView(R.id.direction_long) TextView mDirectionView;
    @BindView(R.id.description_short) TextView mStepView;
    @BindView(R.id.divider) TextView mDivider;
    @BindView(R.id.watch_video) ImageView mImageView;

    // Constructor for the Holder
    public DirectionsHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    // Will Display the title of the Recipe
    public TextView getTitleView() {
        return mTitleView;
    }

    // Will display a long string of instructions for this step
    public TextView getDirectionView() {
        return mDirectionView;
    }

    // Will display a short description of the current step
    public TextView getStepView() {
        return mStepView;
    }

    public ImageView getImageView() {
        return mImageView;
    }

    public void setTitleTextView(TextView titleText) {
        mTitleView = titleText;
    }

    public void setDirectionView(TextView directionText) {
        mDirectionView = directionText;
    }

    public void setStepView(TextView stepText) {
        mStepView = stepText;
    }
}
