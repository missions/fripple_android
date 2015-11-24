package com.missions.fripple.activities.custom;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Lemuel Castro on 11/24/2015.
 */
public class CustomCoordinatorLayout extends CoordinatorLayout {
    private boolean isEnableExpand = true;

    public CustomCoordinatorLayout(Context context) {
        super(context);
    }

    public CustomCoordinatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomCoordinatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return isEnableExpand && super.onStartNestedScroll(child, target, nestedScrollAxes);
    }

    public void enableExpand(boolean isEnableExpand){
        this.isEnableExpand = isEnableExpand;
    }
}
