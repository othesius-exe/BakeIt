package com.example.caleb.bakeit.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.example.caleb.bakeit.R;
import com.example.caleb.bakeit.Recipe;
import com.example.caleb.bakeit.RecipeDirections;
import com.example.caleb.bakeit.RecipeIngredients;
import com.example.caleb.bakeit.adapters.RecipeInfoPagerAdapter;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 */

public class DirectionsActivity extends FragmentActivity implements DirectionsFragment.OnVideoSelectedListener {

    private String LOG_TAG = DirectionsActivity.class.getSimpleName();

    // ViewPagerAdapter
    private RecipeInfoPagerAdapter mRecipeInfoPagerAdapter;

    // FragmentManager
    private FragmentManager mSupportFragmentManager;

    // Objects
    private Recipe mRecipe;
    private ArrayList<RecipeDirections> mRecipeDirectionsArrayList;
    private ArrayList<RecipeIngredients> mRecipeIngredientsArrayList;

    // Bundles
    private Bundle mBundle;

    @BindView(R.id.view_pager) ViewPager mViewPager;
    @BindView(R.id.exo_player) SimpleExoPlayerView mExoPlayerView;

    private SimpleExoPlayer mExoPlayer;
    public String mUrl;
    private Uri mUri;

    private String directionsTitle = "Directions";
    private String ingredientsTitle = "Ingredients";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directions);
        ButterKnife.bind(this);
        mSupportFragmentManager = getSupportFragmentManager();
        mBundle = new Bundle();

        // Get the starting intent
        Intent intent = getIntent();
        // Create a Recipe Object from the extras that were shipped with the intent
        mRecipe = intent.getParcelableExtra("recipe");

        if (mRecipe != null) {
            // Get the name of the recipe, so we can populate the title page
            // Draw the ArrayLists of Directions and Ingredients out
            mRecipeDirectionsArrayList = mRecipe.getDirections();
            mRecipeIngredientsArrayList = mRecipe.getIngredients();
        }

        mBundle.putParcelableArrayList("directions", mRecipeDirectionsArrayList);
        mBundle.putString("title", "Directions");
        mBundle.putParcelableArrayList("ingredients", mRecipeIngredientsArrayList);
        mBundle.putString("title", "Ingredients");

        // Check for a savedInstance
        // Otherwise create a new instance
        if (savedInstanceState == null) {
            DirectionsFragment directionsFragment = DirectionsFragment.newInstance(directionsTitle, mRecipeDirectionsArrayList, this);

            directionsFragment.setArguments(mBundle);
            mSupportFragmentManager.beginTransaction()
                    .add(R.id.view_pager, directionsFragment)
                    .commit();

            IngredientFragment ingredientFragment = IngredientFragment.newInstance(ingredientsTitle, mRecipeIngredientsArrayList);
            ingredientFragment.setArguments(mBundle);
            mSupportFragmentManager.beginTransaction()
                    .add(R.id.view_pager, ingredientFragment)
                    .commit();
        }

        mRecipeInfoPagerAdapter = new RecipeInfoPagerAdapter(this, mBundle, mSupportFragmentManager);
        mViewPager.setAdapter(mRecipeInfoPagerAdapter);
        mViewPager.setOffscreenPageLimit(1);

        // Create an ExoPlayer instance
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getApplicationContext(), getString(R.string.app_name));
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

        mExoPlayer = ExoPlayerFactory.newSimpleInstance(getApplicationContext(), trackSelector);
        mExoPlayerView.setPlayer(mExoPlayer);

        MediaSource videoSource = new ExtractorMediaSource(mUri, dataSourceFactory, extractorsFactory, null, null);
        mExoPlayer.prepare(videoSource);
    }

    @Override
    public void getUrl(String url) {
        mUri = Uri.parse(url);
    }
}
