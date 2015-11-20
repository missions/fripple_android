package com.missions.fripple.activities.custom;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


/**
 * Created by Lemuel Castro on 10/12/2015.
 */
public class CustomFragment extends Fragment {

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        Animation anim = null;

        if(nextAnim == 0){
            anim = enter?enterAnimation():exitAnimation();
            if(anim != null){
                addTransitionBackToggle(anim);
            }
        }else{
            anim = AnimationUtils.loadAnimation(getActivity(), nextAnim);
            addTransitionBackToggle(anim);
        }

        return anim;
    }

    protected void addTransitionBackToggle(Animation anim){
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                ((CustomAppCompatActivity) getActivity()).setAllowedBackButton(false);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ((CustomAppCompatActivity) getActivity()).setAllowedBackButton(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public Animation enterAnimation(){
        return null;
    }

    public Animation exitAnimation(){
        return null;
    }

    public boolean overrideOnBackPressed(){
        return false;
    }

    public void popFragment(){
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.remove(this);
        ft.commit();
        getActivity().getSupportFragmentManager().popBackStack();
    }

    public CustomAppCompatActivity getAppCompatActivity(){
        return ((CustomAppCompatActivity)getActivity());
    }
}
