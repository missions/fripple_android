package com.missions.fripple.activities.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.Profile;
import com.missions.fripple.R;
import com.missions.fripple.activities.activities.Home;

import com.missions.fripple.activities.singletons.FacebookSession;

/**
 * Created by Lemuel on 8/16/2015.
 */
public class SignUpFragment extends Fragment implements FacebookSession.SessionListener {

    private Button register;
    private Button fb_credentials_use;
    private FacebookSession fbSession;
    private TextView uname, fname, lname, password, confirmPassword;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sign_up, container, false);
        fbSession = FacebookSession.getInstance();

        fb_credentials_use = (Button)view.findViewById(R.id.btn_signup_fb);
        uname = (TextView)view.findViewById(R.id.username);
        lname = (TextView)view.findViewById(R.id.realname);
        password = (TextView)view.findViewById(R.id.password);
        confirmPassword = (TextView)view.findViewById(R.id.confirm_password);

        fb_credentials_use.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fbSession.isLoggedIn()) {
                    fillInCredentials(fbSession.getProfile());
                }else{
                    fbSession.loginFacebook((Activity)inflater.getContext(), SignUpFragment.this);
                }
            }
        });

        return view;
    }

    private void fillInCredentials(Profile profile){
        fname.setText(profile.getFirstName());
        lname.setText(profile.getLastName());
    }

    @Override
    public void onProfileChanged() {
        if (fbSession.isLoggedIn()) {
            fillInCredentials(fbSession.getProfile());
        }
    }

    @Override
    public void onAccessTokenChange() {
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if(nextAnim==0){
            return null;
        }

        Animation anim = AnimationUtils.loadAnimation(getActivity(), nextAnim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ((Home)getActivity()).setLockState(false);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        return anim;
    }
}
