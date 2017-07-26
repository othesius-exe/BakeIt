package com.example.caleb.bakeit.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.caleb.bakeit.R;
import com.example.caleb.bakeit.RecipeIngredients;

import java.util.ArrayList;

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

    public static IngredientFragment newInstance(String title, ArrayList<RecipeIngredients> ingredients) {
        IngredientFragment ingredientFragment = new IngredientFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("ingredients", ingredients);
        args.putString("title", title);
        ingredientFragment.setArguments(args);
        return ingredientFragment;
    }

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
