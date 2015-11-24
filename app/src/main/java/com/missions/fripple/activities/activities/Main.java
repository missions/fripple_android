package com.missions.fripple.activities.activities;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;

import com.missions.fripple.R;
import com.missions.fripple.activities.adapters.ViewPagerAdapter;
import com.missions.fripple.activities.custom.CustomCoordinatorLayout;
import com.missions.fripple.activities.custom.CustomDrawerActivity;
import com.missions.fripple.activities.fragments.FollowingTopFragment;
import com.missions.fripple.activities.fragments.TopFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Lemuel Castro on 11/24/2015.
 */
public class Main extends CustomDrawerActivity {

    @Bind(R.id.fragment_holder)
    View fragmentHolder;

    @Bind(R.id.backdrop)
    View imageback;

    @Bind(R.id.collapsing_view_content)
    FrameLayout collapsingContent;

    AppBarLayout appBarLayout;

    CustomCoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    private TabLayout tabLayout;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        ViewPager viewPager = new ViewPager(this);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFrag(new FollowingTopFragment(), "Following");
        viewPagerAdapter.addFrag(new Fragment(), "Trending");
        viewPagerAdapter.addFrag(new Fragment(), "Newest");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        coordinatorLayout = (CustomCoordinatorLayout) findViewById(R.id.coordinatorLayout);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingContent.addView(getLayoutInflater().inflate(R.layout.collapsing_view, collapsingContent, false));
        TopFragment topFragment = new TopFragment();
        addFragmentToViewAndBackStack(ADD, TopFragment.class.getSimpleName(), R.id.fragment_holder, topFragment).commit();
    }

    public TabLayout getTabLayout(){
        return tabLayout;
    }

}
