package com.missions.fripple.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.missions.fripple.R;

import fragments.LoginFragment;
import fragments.SignUpFragment;
import singletons.FacebookSession;

import android.support.v4.app.FragmentTransaction;

/**
 * Created by Lemuel on 8/16/2015.
 */
public class Home extends FragmentActivity {

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
        signUpFragment = new SignUpFragment();

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
    public void onBackPressed() {
        if(onSignUpPage){
            changeFragmentState();
            return;
        }
        super.onBackPressed();
    }

    private void changeFragmentState(){
        if(lockTransition==true){
            return;
        }

        onSignUpPage = !onSignUpPage;
        lockTransition = true;
        signUpLoginButton.setText(getString(onSignUpPage?R.string.log_in:R.string.sign_up));
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(onSignUpPage?R.anim.slide_up_enter:R.anim.slide_down_enter, onSignUpPage?R.anim.slide_up_exit:R.anim.slide_down_exit);
        ft.remove(onSignUpPage?loginFragment:signUpFragment);
        ft.add(R.id.fragment_container, onSignUpPage?signUpFragment:loginFragment);
        ft.commit();
    }

    public void setLockState(boolean isLocked){
        lockTransition = isLocked;
    }
}
