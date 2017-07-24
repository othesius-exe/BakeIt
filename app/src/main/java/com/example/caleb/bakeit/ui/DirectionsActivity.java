package com.example.caleb.bakeit.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.example.caleb.bakeit.R;

import butterknife.BindView;

/**
 *
 */

public class DirectionsActivity extends FragmentActivity {

    @BindView(R.id.view_pager) ViewPager mViewPager;

    // ViewPagerAdapter
    private RecipeInfoPagerAdapter mRecipeInfoPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directions);

        mViewPager.setAdapter(mRecipeInfoPagerAdapter);
    }
}
