package com.missions.fripple.activities.custom;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by Lemuel Castro on 11/24/2015.
 */
public class CustomGridLayoutManager extends GridLayoutManager {

    private Context context;
    private static final float MILLIS_PER_PIXEL = 100f;
    private static final int X_DIRECTION = 1;
    private static final float PIXEL_PER_MILLIS = 50f;

    public CustomGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
        this.context = context;
    }

    public CustomGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
        this.context = context;
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {

        LinearSmoothScroller scroller = new LinearSmoothScroller(context) {
            @Override
            public PointF computeScrollVectorForPosition(int targetPosition) {
                return new PointF(X_DIRECTION, 0);
            }

            @Override
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return MILLIS_PER_PIXEL/ TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, PIXEL_PER_MILLIS, displayMetrics);
            }
        };
        scroller.setTargetPosition(position);
        startSmoothScroll(scroller);
    }
}
