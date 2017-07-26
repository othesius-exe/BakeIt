package com.example.caleb.bakeit.ui;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.caleb.bakeit.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ViewHolder for a RecipeDirections Object
 */

public class DirectionsHolder extends RecyclerView.ViewHolder {

    @Nullable
    @BindView(R.id.card_title_view) TextView mTitleView;
    @BindView(R.id.direction_long) TextView mDirectionView;
    @BindView(R.id.direction_step) TextView mStepView;

    public DirectionsHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public TextView getTitleView() {
        return mTitleView;
    }

    public TextView getDirectionView() {
        return mDirectionView;
    }

    public TextView getStepView() {
        return mStepView;
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
