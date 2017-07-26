package com.example.caleb.bakeit.ui;

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
    private Bundle mBundle;
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
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ingredients_fragment, container, false);

        ButterKnife.bind(getContext(), mIngredientRecycler);

        mBundle = getArguments();
        mRecipeIngredientsArray = new ArrayList<>();
        mRecipeIngredientsArray = mBundle.getParcelableArrayList("ingredients");
        mIngredientObjectsArray = new ArrayList<>();
        mIngredientObjectsArray.add(getLoaderManager());
        mIngredientsAdapter = new RecipeAdapter(getContext(), mIngredientObjectsArray);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mIngredientRecycler.setLayoutManager(mLayoutManager);
        mIngredientRecycler.setAdapter(mIngredientsAdapter);
        return v;
    }
}
