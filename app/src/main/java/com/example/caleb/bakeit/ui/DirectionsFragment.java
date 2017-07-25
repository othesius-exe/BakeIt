package com.example.caleb.bakeit.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.caleb.bakeit.R;

/**
 *
 */

public class DirectionsFragment extends Fragment {

    // Log Tag
    private static final String LOG_TAG = DirectionsFragment.class.getSimpleName();

    // Constructor
    public DirectionsFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_layout, container, false);

        return view;
    }
}
