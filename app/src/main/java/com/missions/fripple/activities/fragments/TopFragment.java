package com.missions.fripple.activities.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.missions.fripple.R;
import com.missions.fripple.activities.activities.Main;
import com.missions.fripple.activities.adapters.CardAdapterNewEstablishment;
import com.missions.fripple.activities.adapters.ViewPagerAdapter;
import com.missions.fripple.activities.custom.CustomFragment;
import com.missions.fripple.activities.custom.CustomGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lemuel Castro on 11/24/2015.
 */
public class TopFragment extends CustomFragment {

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("lem", "called");
        View v = inflater.inflate(R.layout.fragment_layout_top, container, false);
        viewPager = (ViewPager) v.findViewById(R.id.viewpager);

        setUpCollapsingView(inflater);
        setUpTabLayout();


        return v;
    }

    private void setUpCollapsingView(LayoutInflater inflater) {
        View collapsingView = inflater.inflate(R.layout.collapsing_view_new_est, null);
        RecyclerView recyclerView = (RecyclerView) collapsingView.findViewById(R.id.recyclerView_new_estabishment);
        CustomGridLayoutManager gridLayoutManager = new CustomGridLayoutManager(getActivity(), 1, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        List<String> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add("");
        }

        recyclerView.setAdapter(new CardAdapterNewEstablishment(list));
        ((Main) getActivity()).addCollapsingView(collapsingView);
    }

    private void setUpTabLayout() {
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFrag(new FollowingTopFragment(), "Following");
        viewPagerAdapter.addFrag(new Fragment(), "Trending");
        viewPagerAdapter.addFrag(new Fragment(), "Newest");
        viewPager.setAdapter(viewPagerAdapter);


        ((Main) getActivity()).getTabLayout().setupWithViewPager(viewPager);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((Main)getActivity()).isTabVisible(true);
    }
}
