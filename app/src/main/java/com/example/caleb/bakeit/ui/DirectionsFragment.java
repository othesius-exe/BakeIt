package com.example.caleb.bakeit.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.caleb.bakeit.R;
import com.example.caleb.bakeit.RecipeDirections;

import java.util.ArrayList;

/**
 *
 */

public class DirectionsFragment extends Fragment {

    // Log Tag
    private static final String LOG_TAG = DirectionsFragment.class.getSimpleName();

    // Constructor
    public static DirectionsFragment newInstance(String title, ArrayList<RecipeDirections> directions) {
        DirectionsFragment directionsFragment = new DirectionsFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("directions", directions);
        args.putString("title", title);
        directionsFragment.setArguments(args);
        return directionsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<RecipeDirections> directions = getArguments().getParcelableArrayList("directions");
        String title = getArguments().getString("title");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_layout, container, false);

        return view;
    }
}
