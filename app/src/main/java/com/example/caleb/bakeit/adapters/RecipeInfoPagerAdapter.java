package com.example.caleb.bakeit.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.caleb.bakeit.RecipeDirections;
import com.example.caleb.bakeit.RecipeIngredients;
import com.example.caleb.bakeit.ui.DirectionsFragment;
import com.example.caleb.bakeit.ui.IngredientFragment;

import java.util.ArrayList;

/**
 *
 */

public class RecipeInfoPagerAdapter extends FragmentStatePagerAdapter {

    private String mIngredientsFragmentName = "Ingredients";
    private String mDirectionsFragmentName = "Directions";
    private Context mContext;
    private Bundle mBundle;
    private ArrayList<RecipeDirections> mDirections;
    private ArrayList<RecipeIngredients> mIngredients;
    private DirectionsFragment.OnVideoSelectedListener mListener;
    private static int FRAGMENTS = 2;
    private String mTitle;


    public RecipeInfoPagerAdapter(Context context, Bundle bundle, FragmentManager fragmentManager) {
        super(fragmentManager);
        mContext = context;
        mBundle = bundle;
        mDirections = mBundle.getParcelableArrayList("directions");
        mIngredients = mBundle.getParcelableArrayList("ingredients");
        mTitle = mBundle.getString("title");

    }

    @Override
    public Fragment getItem(int position) {

        switch(position) {
            case 0:
                return IngredientFragment.newInstance(mIngredientsFragmentName, mIngredients);
            case 1:
                return DirectionsFragment.newInstance(mDirectionsFragmentName, mDirections, mListener);
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mIngredientsFragmentName;
        } else if (position == 1) {
            return mDirectionsFragmentName;
        }
        return null;

    }

    @Override
    public int getCount() {
        return FRAGMENTS;
    }

}
