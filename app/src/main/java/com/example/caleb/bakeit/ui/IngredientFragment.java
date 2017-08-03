package com.example.caleb.bakeit.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.caleb.bakeit.R;
import com.example.caleb.bakeit.RecipeIngredients;
import com.example.caleb.bakeit.adapters.RecipeAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 */

public class IngredientFragment extends Fragment {

    private RecipeAdapter mIngredientsAdapter;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<RecipeIngredients> mRecipeIngredientsArray;
    private ArrayList<Object> mIngredientObjectsArray;
    private String mTitle;
    private Bundle mBundle;
    private Context mContext;
    private boolean isTablet = false;
    private boolean isLandscape = false;
    @BindView(R.id.ingredients_recycler) RecyclerView mIngredientRecycler;

    public static IngredientFragment newInstance(String title, ArrayList<RecipeIngredients> ingredients) {
        IngredientFragment ingredientFragment = new IngredientFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("ingredients", ingredients);
        args.putString("title", title);
        ingredientFragment.setArguments(args);
        return ingredientFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitle = getArguments().getString("title");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ingredients_fragment, container, false);

        ButterKnife.bind(this, v);

        mBundle = getArguments();
        mRecipeIngredientsArray = new ArrayList<>();
        mRecipeIngredientsArray = mBundle.getParcelableArrayList("ingredients");
        mIngredientObjectsArray = new ArrayList<Object>(mRecipeIngredientsArray);
        mIngredientsAdapter = new RecipeAdapter(getContext(), mIngredientObjectsArray);
        mLayoutManager = new LinearLayoutManager(getContext());

        mContext = getContext();
        isTablet = isTablet(mContext);
        isLandscape = isLandscape(mContext);

        if (isTablet) {
            if (isLandscape) {
                mIngredientRecycler.setVisibility(View.INVISIBLE);
            } else {
                mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                mLayoutManager.setReverseLayout(false);
            }
        } else if (!isTablet && isLandscape){
            mIngredientRecycler.setVisibility(View.INVISIBLE);
        } else {
            mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mLayoutManager.setReverseLayout(false);
        }

        mIngredientRecycler.setLayoutManager(mLayoutManager);
        mIngredientRecycler.setAdapter(mIngredientsAdapter);
        return v;
    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static boolean isLandscape(Context context) {
        return (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE);
    }
}
