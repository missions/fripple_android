package com.missions.fripple.activities.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.missions.fripple.R;
import com.missions.fripple.activities.adapters.SampleAdapter;
import com.missions.fripple.activities.custom.CustomFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lemuel Castro on 11/24/2015.
 */
public class FollowingTopFragment extends CustomFragment {

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("lem", "following");

        View v = inflater.inflate(R.layout.following_layout, container, false);
        recyclerView = (RecyclerView)v.findViewById(R.id.recyclerView);

        List<String> list = new ArrayList<>();

        for(int i=0;i<10;i++){
            list.add("");
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new SampleAdapter(list));

        return v;
    }
}
