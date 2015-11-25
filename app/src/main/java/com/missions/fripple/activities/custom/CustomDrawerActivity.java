package com.missions.fripple.activities.custom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.missions.fripple.R;
import com.missions.fripple.activities.singletons.FacebookSession;


//Fragments to be added to this Activity must be added in the backstack
public class CustomDrawerActivity extends CustomAppCompatActivity {

    protected DrawerLayout drawerLayout;
    protected ActionBarDrawerToggle actionBarToggle;
    protected FacebookSession fbSession;
    protected CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        setupActionBar();

        fbSession = FacebookSession.getInstance();
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        actionBarToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.lname_hint, R.string.log_in) {

            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
                additonalActionOnDrawerOpened();
            }


            public void onDrawerClosed(View view) {
                invalidateOptionsMenu();
                additonalActionOnDrawerClosed();
            }
        };

        actionBarToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(actionBarToggle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarToggle.onOptionsItemSelected(item)) {
            return true;
        }
        drawerLayout.closeDrawers();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarToggle.syncState();
    }

    protected int getLayout() {
        return R.layout.main;
    }

    @Override
    public void setupActionBar() {
        Log.i("lem", "this2");
        super.setupActionBar();
    }

    public void additonalActionOnDrawerClosed(){

    }

    public void additonalActionOnDrawerOpened(){

    }
}
