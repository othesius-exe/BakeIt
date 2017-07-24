package com.example.caleb.bakeit.ui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 *
 */

public class RecipeInfoPagerAdapter extends FragmentStatePagerAdapter {

    private String mIngredientsFragmentName = "Ingredients Fragment";
    private String mDirectionsFragmentName = "Directions Fragment";

    private Context mContext;


    public RecipeInfoPagerAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        switch(position) {
            case 0:
                return IngredientFragment.newInstance(mIngredientsFragmentName);
            case 1:
                return DirectionsFragment.instantiate(mContext, mDirectionsFragmentName);
            default:
                return IngredientFragment.instantiate(mContext, mIngredientsFragmentName);
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
