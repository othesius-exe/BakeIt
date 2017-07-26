package com.example.caleb.bakeit;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Recipe Ingredients Object
 */


public class RecipeIngredients implements Parcelable {

    private String mIngredient;
    private double mQuantity;
    private String mMeasurement;

    public RecipeIngredients(String ingredient, String measurement, double quantity) {
        mIngredient = ingredient;
        mMeasurement = measurement;
        mQuantity = quantity;
    }

    public String getIngredient() {
        return mIngredient;
    }

    public String getMeasurement() {
        return mMeasurement;
    }

    public double getQuantity() {
        return mQuantity;
    }

    protected RecipeIngredients(Parcel in) {
        mIngredient = in.readString();
        mQuantity = in.readDouble();
        mMeasurement = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mIngredient);
        dest.writeDouble(mQuantity);
        dest.writeString(mMeasurement);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<RecipeIngredients> CREATOR = new Parcelable.Creator<RecipeIngredients>() {
        @Override
        public RecipeIngredients createFromParcel(Parcel in) {
            return new RecipeIngredients(in);
        }

        @Override
        public RecipeIngredients[] newArray(int size) {
            return new RecipeIngredients[size];
        }
    };
}