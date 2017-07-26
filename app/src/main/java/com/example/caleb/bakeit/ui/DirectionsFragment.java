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
import com.example.caleb.bakeit.RecipeDirections;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 */

public class DirectionsFragment extends Fragment {

    private RecipeAdapter mDirectionsAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private ArrayList<Object> mDirectionsObject;
    private ArrayList<RecipeDirections> mDirections;
    @BindView(R.id.recipe_recycler) RecyclerView mRecyclerView;

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
        mDirections = getArguments().getParcelableArrayList("directions");
        String title = getArguments().getString("title");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.directions_fragment, container, false);

        ButterKnife.bind(this, mRecyclerView);
        mDirectionsAdapter = new RecipeAdapter(getContext(), mDirectionsObject);
        mRecyclerView.setAdapter(mDirectionsAdapter);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        return view;
    }
}
