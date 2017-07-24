package com.example.caleb.bakeit;

/**
 * RecipeDirections Object
 */

public class RecipeDirections {

    // RecipeDirections Components
    // Instructions
    private String mStepNumber;

    private String mStepContent;

    private String mVideoUrl;

    public RecipeDirections(String stepNumber, String stepContent) {
        mStepContent = stepContent;
        mStepNumber = stepNumber;
    }

    public RecipeDirections(String stepNumber, String stepContent, String videoUrl) {
        mStepContent = stepContent;
        mStepNumber = stepNumber;
        mVideoUrl = videoUrl;
    }

    public String getStepNumber() {
        return mStepNumber;
    }

    public String getStepContent() {
        return mStepContent;
    }

    public String getVideoUrl() {
        return mVideoUrl;
    }
}
