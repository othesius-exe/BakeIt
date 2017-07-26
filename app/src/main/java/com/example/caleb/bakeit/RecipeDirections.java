package com.example.caleb.bakeit;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * RecipeDirections Object
 */

public class RecipeDirections implements Parcelable {

    // RecipeDirections Components
    // Instructions
    private int mStepNumber;

    private String mStepDescription;

    private String mStepContent;

    private String mVideoUrl;

    public RecipeDirections(int stepNumber, String stepDescription, String stepContent, String videoUrl) {
        mStepContent = stepContent;
        mStepNumber = stepNumber;
        mStepDescription = stepDescription;
        mVideoUrl = videoUrl;
    }

    public int getStepNumber() {
        return mStepNumber;
    }

    public String getStepContent() {
        return mStepContent;
    }

    public String getStepDescription() {
        return mStepDescription;
    }

    public String getVideoUrl() {
        return mVideoUrl;
    }

    protected RecipeDirections(Parcel in) {
        mStepNumber = in.readInt();
        mStepContent = in.readString();
        mVideoUrl = in.readString();
        mStepDescription = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mStepNumber);
        dest.writeString(mStepContent);
        dest.writeString(mVideoUrl);
        dest.writeString(mStepDescription);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<RecipeDirections> CREATOR = new Parcelable.Creator<RecipeDirections>() {
        @Override
        public RecipeDirections createFromParcel(Parcel in) {
            return new RecipeDirections(in);
        }

        @Override
        public RecipeDirections[] newArray(int size) {
            return new RecipeDirections[size];
        }
    };
}
