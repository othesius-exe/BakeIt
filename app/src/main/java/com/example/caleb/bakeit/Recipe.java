package com.example.caleb.bakeit;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 *
 */

public class Recipe implements Parcelable {

    private ArrayList<RecipeDirections> mDirections;
    private ArrayList<RecipeIngredients> mIngredients;
    private String mTitle;

    public Recipe(String title, ArrayList<RecipeDirections> directions, ArrayList<RecipeIngredients> ingredients) {
        mTitle = title;
        mDirections = directions;
        mIngredients = ingredients;
    }

    public ArrayList<RecipeDirections> getDirections() {
        return mDirections;
    }

    public ArrayList<RecipeIngredients> getIngredients() {
        return mIngredients;
    }

    public String getTitle() {
        return mTitle;
    }

    protected Recipe(Parcel in) {
        if (in.readByte() == 0x01) {
            mDirections = new ArrayList<RecipeDirections>();
            in.readList(mDirections, RecipeDirections.class.getClassLoader());
        } else {
            mDirections = null;
        }
        if (in.readByte() == 0x01) {
            mIngredients = new ArrayList<RecipeIngredients>();
            in.readList(mIngredients, RecipeIngredients.class.getClassLoader());
        } else {
            mIngredients = null;
        }
        mTitle = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (mDirections == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(mDirections);
        }
        if (mIngredients == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(mIngredients);
        }
        dest.writeString(mTitle);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}
