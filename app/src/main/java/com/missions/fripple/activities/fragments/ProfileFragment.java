package com.missions.fripple.activities.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.missions.fripple.R;
import com.missions.fripple.activities.activities.Main;
import com.missions.fripple.activities.custom.CustomFragment;
import com.missions.fripple.activities.singletons.FacebookSession;
import com.missions.fripple.activities.utils.CircleTransform;

/**
 * Created by Lemuel Castro on 11/23/2015.
 */
public class ProfileFragment extends CustomFragment {

    private FacebookSession fbSession;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fbSession = FacebookSession.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.profile, container, false);
        View collapsingView = inflater.inflate(R.layout.collapsing_view_profile, (ViewGroup)((Main)getActivity()).getCollapsingLayout(), false);

        ((TextView)collapsingView.findViewById(R.id.username)).setText(fbSession.getProfile().getName());
        Glide.with(this).load(fbSession.getProfile().getProfilePictureUri(200, 200)).transform(new CircleTransform(getContext())).into((ImageView) collapsingView.findViewById(R.id.profile_pic));

        ((Main) getActivity()).addCollapsingView(collapsingView);
        ((Main)getActivity()).isTabVisible(false);
        return mainView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((Main)getActivity()).isTabVisible(false);
    }

    @Override
    public boolean overrideOnBackPressed() {
        getActivity().finish();
        return true;
    }
}
