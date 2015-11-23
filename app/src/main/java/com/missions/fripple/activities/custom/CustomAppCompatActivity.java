package com.missions.fripple.activities.custom;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextSwitcher;

import com.missions.fripple.R;
import com.missions.fripple.activities.utils.Utils;

/**
 * Created by Lemuel Castro on 10/13/2015.
 */
public class CustomAppCompatActivity extends AppCompatActivity {

    protected TextSwitcher title;

    protected Toolbar toolbar;
    protected boolean isAllowedBackButton = true;

    protected int[] windowDimension;

    public static final int ADD = 0;
    public static final int REPLACE = 1;
    private static final int TEXT_SIZE = 15;

    private int textColor;
    private String currentTitle;
    private String pastTitle;

    public static final String PAST_ACTIVITY_TITLE = "past_title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pastTitle = getIntent().getStringExtra(PAST_ACTIVITY_TITLE);

        textColor = getResources().getColor(R.color.white);

        windowDimension = Utils.getWndowDimensions(this);
    }

    public void setAllowedBackButton(boolean isAllowedBackButton) {
        this.isAllowedBackButton = isAllowedBackButton;
    }

    @Override
    protected void onResume() {
        if(pastTitle!=null){

        }
        super.onResume();
    }


    public android.support.v4.app.FragmentTransaction addFragmentToViewAndBackStack(int action, String backStackID, int fragmentParent, android.support.v4.app.Fragment fragment, int enterAnim, int exitAnim) {
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(enterAnim, exitAnim);
        ft.addToBackStack(backStackID);
        switch (action) {
            case ADD:
                ft.add(fragmentParent, fragment, backStackID);
                break;
            case REPLACE:
                ft.replace(fragmentParent, fragment, backStackID);
                break;
        }

        return ft;
    }

    public android.support.v4.app.FragmentTransaction addFragmentToViewAndBackStack(int action, String backStackID, int fragmentParent, android.support.v4.app.Fragment fragment) {
        return addFragmentToViewAndBackStack(action, backStackID, fragmentParent, fragment, -1, -1);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
       // setupActionBar();
    }

    //Disallow overriding
    @Override
    public final void onBackPressed() {
        if (!isAllowedBackButton) {
            return;
        }

        if (isBackOverriden()) {
            return;
        }

        doOnBackPressed();

        super.onBackPressed();
    }

    public void setupActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    protected boolean isBackOverriden() {
        FragmentManager.BackStackEntry backStackEntry;
        CustomFragment customFragment = null;

        try {
            backStackEntry = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1);
            customFragment = (CustomFragment) getSupportFragmentManager().findFragmentByTag(backStackEntry.getName());
        } catch (Exception e) {
            Log.e("ERROR", "You may have not added the fragment in the backstack, all added fragments in the activity must be added in the backstack");
            e.printStackTrace();
            return false;
        }

        return customFragment.overrideOnBackPressed();
    }


    /*define normal onbackpressed functionalities here*/
    protected boolean doOnBackPressed() {
        //PUT METHODS TO BE DONE ADDITTIONALY ON BACK PRESSED\
        return false;
    }

    protected String getDefaultTitle() {
        return getResources().getString(R.string.app_name);
    }

    public String getActionBarTitle(){
        return currentTitle;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public int[] getWindowDimension() {
        return windowDimension;
    }

    public void setWindowDimension(int[] windowDimension) {
        this.windowDimension = windowDimension;
    }

}


