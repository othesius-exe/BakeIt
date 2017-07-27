package com.example.caleb.bakeit.ui;

import android.content.Context;
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
import com.example.caleb.bakeit.adapters.RecipeAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 */

public class DirectionsFragment extends Fragment {

    private RecipeAdapter mDirectionsAdapter;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<RecipeDirections> mRecipeDirectionsArray;
    private ArrayList<Object> mDirectionsObjectsArray;
    private Bundle mBundle;
    private boolean isTablet = true;
    @BindView(R.id.directions_recycler) RecyclerView mDirectionRecycler;

    private static DirectionsFragment.OnVideoSelectedListener mListener;


    public interface OnVideoSelectedListener {
        void prepMediaPlayer(String url);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (OnVideoSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Must Implement Listener");
        }
    }

    // Log Tag
    private static final String LOG_TAG = DirectionsFragment.class.getSimpleName();

    // Constructor
    public static DirectionsFragment newInstance(String title, ArrayList<RecipeDirections> directions, OnVideoSelectedListener listener) {
        DirectionsFragment directionsFragment = new DirectionsFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("directions", directions);
        args.putString("title", title);
        directionsFragment.setArguments(args);
        mListener = listener;
        return directionsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.directions_fragment, container, false);

        ButterKnife.bind(this, view);

        mBundle = getArguments();
        mDirectionsObjectsArray = new ArrayList<>();
        mRecipeDirectionsArray = mBundle.getParcelableArrayList("directions");
        mDirectionsObjectsArray = new ArrayList<Object>(mRecipeDirectionsArray);
        mDirectionsAdapter = new RecipeAdapter(getContext(), this, mDirectionsObjectsArray);
        mLayoutManager = new LinearLayoutManager(getContext());
        if (isTablet) {
            if (getResources().getConfiguration().orientation == 1) {
                mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                mLayoutManager.setReverseLayout(false);
            } else {
                mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                mLayoutManager.setReverseLayout(false);
            }
        } else {
            mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mLayoutManager.setReverseLayout(false);
        }



        mDirectionRecycler.setLayoutManager(mLayoutManager);
        mDirectionRecycler.setAdapter(mDirectionsAdapter);

        return view;
    }

    public void setUri(String url) {
        mListener.prepMediaPlayer(url);
    }

}
