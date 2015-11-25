package com.missions.fripple.activities.custom;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

/**
 * Created by Lemuel Castro on 11/25/2015.
 */
public class FadeBehavior extends CoordinatorLayout.Behavior<View> {
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        Log.i("lem", dependency.getClass().getSimpleName());
        return dependency instanceof Toolbar;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        Log.i("lem", dependency.getHeight()+"height");
        return super.onDependentViewChanged(parent, child, dependency);
    }
}
