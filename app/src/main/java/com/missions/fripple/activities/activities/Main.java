package com.missions.fripple.activities.activities;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.missions.fripple.R;
import com.missions.fripple.activities.adapters.DrawerAdapter;
import com.missions.fripple.activities.custom.CustomCoordinatorLayout;
import com.missions.fripple.activities.custom.CustomDrawerActivity;
import com.missions.fripple.activities.fragments.ProfileFragment;
import com.missions.fripple.activities.fragments.TopFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Lemuel Castro on 11/24/2015.
 */
public class Main extends CustomDrawerActivity implements DrawerAdapter.DrawerItemClickListener {

    @Bind(R.id.fragment_holder)
    View fragmentHolder;

    @Bind(R.id.backdrop)
    View imageback;

    @Bind(R.id.collapsing_view_content)
    FrameLayout collapsingContent;

    @Bind(R.id.listView_drawer)
    ListView drawerListView;

    DrawerAdapter drawerAdapter;

    AppBarLayout appBarLayout;

    CustomCoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    private TabLayout tabLayout;

    private boolean hasPendingSelected;
    private int currentSelected = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        coordinatorLayout = (CustomCoordinatorLayout) findViewById(R.id.coordinatorLayout);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        setUpDrawer();
        addFragmentToViewAndBackStack(ADD, TopFragment.class.getSimpleName(), R.id.fragment_holder, new TopFragment()).commit();
    }

    private void setUpDrawer(){
        List<String> drawerMap = new ArrayList<>();
        drawerMap.add("Home");
        drawerMap.add("Find");
        drawerMap.add("Map");
        drawerMap.add("Profile");

        drawerAdapter = new DrawerAdapter(drawerMap, this);
        drawerListView.addHeaderView(LayoutInflater.from(this).inflate(R.layout.header_drawer, drawerListView, false));
        drawerListView.setAdapter(drawerAdapter);
    }

    public TabLayout getTabLayout(){
        return tabLayout;
    }

    public void addCollapsingView(View view){
        collapsingContent.removeAllViews();
        collapsingContent.addView(view);
    }

    public View getCollapsingLayout(){
        return collapsingContent;
    }

    @Override
    public void onDrawerItemClick(final int position) {
        drawerLayout.closeDrawers();
        drawerAdapter.notifyDataSetChanged();

        hasPendingSelected = true;
        currentSelected = position;
    }

    @Override
    public void additonalActionOnDrawerClosed() {
        if(hasPendingSelected){
            hasPendingSelected = false;
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            switch (currentSelected){
                case DrawerAdapter.HOME:
                    addFragmentToViewAndBackStack(ADD, TopFragment.class.getSimpleName(), R.id.fragment_holder, new TopFragment()).commit();
                    break;
                case DrawerAdapter.PROFILE:
                    appBarLayout.setExpanded(true, true);
                    addFragmentToViewAndBackStack(REPLACE, ProfileFragment.class.getSimpleName(), R.id.fragment_holder, new ProfileFragment()).commit();
                    break;
            }
            appBarLayout.setExpanded(true, true);
        }
    }

    public void setIsToolbarMovable(boolean isToolbarMovable){
        coordinatorLayout.enableExpand(isToolbarMovable);
    }

    public void isTabVisible(boolean isVisible){
        tabLayout.setVisibility(isVisible?View.VISIBLE:View.GONE);
    }
}
