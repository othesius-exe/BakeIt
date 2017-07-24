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

public class DirectionsFragment extends Fragment {

    // Log Tag
    private static final String LOG_TAG = DirectionsFragment.class.getSimpleName();

    // List Index
    private static final String LIST_INDEX = "list_index";

    // List Index Int
    private int mListIndex;

    // RecyclerView for Directions List
    @BindView(R.id.directions_fragment) RecyclerView mRecyclerView;

    // Constructor
    public DirectionsFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.directions_fragment, container, false);

        // TODO set text to proper views by getting the bundle from the starting intent

        return view;
    }
}
