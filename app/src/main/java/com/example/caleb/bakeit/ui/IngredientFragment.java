package com.example.caleb.bakeit.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.caleb.bakeit.R;

import butterknife.BindView;

/**
 *
 */

public class IngredientFragment extends Fragment {

    // Log Tag
    private static final String LOG_TAG = IngredientFragment.class.getSimpleName();

    // List Index
    private static final String LIST_INDEX = "list_index";

    // List Index Int
    private int mListIndex;

    // RecyclerView for Directions List
    @BindView(R.id.ingredients_fragment) RecyclerView mRecyclerView;


    public IngredientFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ingredients_fragment, container, false);

        return v;
    }

    public static IngredientFragment newInstance(String text) {
        IngredientFragment ingredientFragment = new IngredientFragment();
        Bundle bundle = new Bundle();

        ingredientFragment.setArguments(bundle);

        return ingredientFragment;
    }
}
