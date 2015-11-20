package com.missions.fripple.activities.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.missions.fripple.R;
import com.missions.fripple.activities.custom.CustomAppCompatActivity;
import com.missions.fripple.activities.fragments.LoginFragment;
import com.missions.fripple.activities.fragments.SignUpFragment2;
import com.missions.fripple.activities.singletons.FacebookSession;

/**
 * Created by Lemuel on 8/16/2015.
 */
public class Home extends CustomAppCompatActivity {

    private CallbackManager callbackManager;
    private TextView signUpLoginButton;
    private FacebookSession fbSession;
    private Fragment fragment;
    private Fragment loginFragment;
    private Fragment signUpFragment;
    private boolean onSignUpPage;
    private boolean lockTransition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.home);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        loginFragment = new LoginFragment();
        signUpFragment = new SignUpFragment2();

        fbSession = FacebookSession.getInstance();
        signUpLoginButton = (TextView) findViewById(R.id.signup_button);

        signUpLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               changeFragmentState();
            }
        });

        ft.add(R.id.fragment_container, loginFragment);
        ft.commit();

        callbackManager = CallbackManager.Factory.create();
        fbSession.setupLoginListener(callbackManager);
        if (FacebookSession.getInstance().isLoggedIn()) {
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected boolean doOnBackPressed() {
        if(onSignUpPage){
            changeFragmentState();
        }
       return true;
    }

    public void changeFragmentState(){
        if(lockTransition==true){
            return;
        }

        onSignUpPage = !onSignUpPage;
        lockTransition = true;
        signUpLoginButton.setText(getString(onSignUpPage?R.string.log_in:R.string.sign_up));
        addFragmentToViewAndBackStack(REPLACE, SignUpFragment2.class.getSimpleName(), R.id.fragment_container, signUpFragment, R.anim.slide_up_enter, R.anim.slide_up_exit).commit();

    }

    public void setLockState(boolean isLocked){
        lockTransition = isLocked;
    }
}
