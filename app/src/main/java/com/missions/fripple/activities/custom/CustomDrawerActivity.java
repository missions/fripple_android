/*
package com.missions.fripple.activities.custom;

import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.missions.fripple.activities.singletons.FacebookSession;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


//Fragments to be added to this Activity must be added in the backstack
public class CustomDrawerActivity extends CustomAppCompatActivity{

    @Bind(R.id.navigation_drawer)
    protected ListView navigationDrawer;
    protected CallbackManager callbackManager;
    protected DrawerLayout drawerLayout;
    protected ActionBarDrawerToggle actionBarToggle;
    protected List<String> profileDetails;
    protected FacebookSession fbSession;
    protected View headerView;
    protected CoordinatorLayout coordinatorLayout;

    private boolean isFromDrawerAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(getLayout());
        ButterKnife.bind(this);
        setActionBarTitle(getDefaultTitle());

        fbSession = FacebookSession.getInstance();
        callbackManager = CallbackManager.Factory.create();
        profileDetails = new ArrayList<>();

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

        //Move to header
        //profileDetails.add(FacebookUser.getUserName());

        profileDetails.add(getString(R.string.masarap));
        profileDetails.add(getString(R.string.radar));
        profileDetails.add(getString(R.string.my_masarap));
        profileDetails.add(getString(R.string.my_page));
        profileDetails.add(getString(R.string.divider));
        profileDetails.add(getString(FacebookSession.getInstance().isLoggedIn()?R.string.logout:R.string.login));
        profileDetails.add(getString(R.string.about_us));
        profileDetails.add(getString(R.string.help));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        actionBarToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.guest, R.string.drawer_close) {
            */
/** Called when a drawer has settled in a completely open state. *//*

            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
            }

            */
/** Called when a drawer has settled in a completely closed state. *//*

            public void onDrawerClosed(View view) {
                if(isFromDrawerAction){
                    isFromDrawerAction = false;

                    switch (selectedTab){
                        case DitoDrawerAdapter.MASARAP:
                            addFragmentToViewAndBackStack(CustomAppCompatActivity.REPLACE, DitoFragment.class.getSimpleName(), R.id.main_fragment_holder, new DitoFragment()).commit();
                            break;
                        case DitoDrawerAdapter.RADAR:
                            addFragmentToViewAndBackStack(CustomAppCompatActivity.REPLACE, RadarMainFragment.class.getSimpleName(), R.id.main_fragment_holder, new RadarMainFragment()).commit();
                            break;
                    }

                }

                invalidateOptionsMenu();
            }
        };

        actionBarToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(actionBarToggle);

        ditoDrawerAdapter = new DitoDrawerAdapter(this, profileDetails, drawerLayout, selectedTab, this);
        setUpHeaderView();
        navigationDrawer.addHeaderView(headerView);
        navigationDrawer.setAdapter(ditoDrawerAdapter);

        fbSession.setupLoginListener(callbackManager);
    }

    public void setUpHeaderView() {
        headerView = getLayoutInflater().inflate(R.layout.layout_drawer_header, navigationDrawer, false);
        if (fbSession.isLoggedIn()) {
            Glide.with(this).load(fbSession.getProfile().getProfilePictureUri(300, 300)).transform(new CircleTransform(this)).into((ImageView) headerView.findViewById(R.id.imageview_user_photo));
            ((TextView) headerView.findViewById(R.id.textview_user_name)).setText(fbSession.getProfile().getName());
        } else {
            Glide.with(this).load(R.drawable.default_photo).transform(new CircleTransform(this)).into((ImageView) headerView.findViewById(R.id.imageview_user_photo));
            ((TextView) headerView.findViewById(R.id.textview_user_name)).setText(getResources().getString(R.string.guest));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
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

    public void setDrawerStatus(boolean status) {
        actionBarToggle.setDrawerIndicatorEnabled(status);
    }

    public void animateDrawerIcon(final boolean state) {
        ValueAnimator anim = ValueAnimator.ofFloat(0, 1);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float slideOffset = (Float) valueAnimator.getAnimatedValue();
                actionBarToggle.onDrawerSlide(drawerLayout, state ? 1 - slideOffset : slideOffset);

                if (slideOffset == 1.0f) {
                    setDrawerStatus(state);
                }
            }
        });
        anim.setInterpolator(new DecelerateInterpolator());
        anim.setDuration(Constants.DURATION_MEDIUM);
        anim.start();
    }

    @Override
    protected boolean doOnBackPressed() {
        if (drawerLayout.isDrawerOpen(navigationDrawer)) {
            drawerLayout.closeDrawers();
            return true;
        }

        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected int getLayout() {
        return R.layout.activity_splash;
    }

    public void setSelectedTab(int selectedTab) {
        this.selectedTab = selectedTab;
        ditoDrawerAdapter.setCurrentSelected(selectedTab);
        ditoDrawerAdapter.notifyDataSetChanged();
    }

    public CoordinatorLayout getCoordinatorLayout() {
        return coordinatorLayout;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (callbackManager.onActivityResult(requestCode, resultCode, data)) {
            return;
        }
    }

    private void showLoginSnackBar() {
        Snackbar.make(coordinatorLayout, "Login to use this feature!", Snackbar.LENGTH_SHORT).setAction("Login", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginToFB();
            }
        }).show();
    }

    private void loginToFB(){
        FacebookSession.loginFacebook(CustomDrawerActivity.this, this);
    }

    private void showDitoFragment() {
        popAllInBackStackWhileClosingDrawer();

        Animation fadeIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        toolbar.getChildAt(toolbar.getChildCount() - 1).startAnimation(fadeIn);
        toolbar.getChildAt(toolbar.getChildCount() - 1).setVisibility(View.VISIBLE);
    }

    private void showRadarFragment() {
        popAllInBackStackWhileClosingDrawer();

        Animation fadeOut = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);

        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                toolbar.getChildAt(toolbar.getChildCount() - 1).setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        toolbar.getChildAt(toolbar.getChildCount() - 1).startAnimation(fadeOut);

    }

    private void logOutUser() {
        if (FacebookSession.getInstance().isLoggedIn()) {
            showLogoutConfirmation();
        } else {
            startLogInPage();
        }
    }

    private void showLogoutConfirmation() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.logout_confirmation));
        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                LoginManager.getInstance().logOut();
                startLogInPage();
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //do something
            }
        });
        builder.show();
    }

    private void startLogInPage() {
        Intent intent1 = new Intent(this, LoginPage.class);
        startActivity(intent1);
        finish();
    }

    private void popAllInBackStackWhileClosingDrawer() {
        getSupportFragmentManager().popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        drawerLayout.closeDrawers();
        isFromDrawerAction = true;
    }

    @Override
    public void onDrawerItemClicked(int item) {
        switch (item) {
            case DitoDrawerAdapter.MASARAP:
                if (!FacebookSession.getInstance().isLoggedIn()) {
                    showLoginSnackBar();
                    return;
                }
                selectedTab = item;
                showDitoFragment();
                break;
            case DitoDrawerAdapter.RADAR:
                selectedTab = item;
                showRadarFragment();
                break;
            case DitoDrawerAdapter.LOGOUT:
                if(FacebookSession.getInstance().isLoggedIn()){
                    logOutUser();
                }else{
                    loginToFB();
                }
                break;
        }
    }

    @Override
    public void onProfileChanged() {
        if(FacebookSession.getInstance().isLoggedIn()){
            Intent intent1 = new Intent(this, LoginPage.class);
            startActivity(intent1);
            finish();
        }
    }

    @Override
    public void onAccessTokenChange() {

    }
}
*/
