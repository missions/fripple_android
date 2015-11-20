package com.missions.fripple.activities.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Lemuel Castro on 9/30/2015.
 */
public class Utils {

    public static Bitmap getResizedBitmap(Bitmap bm, float newWidth, float newHeight) {
        try {
            return Bitmap.createScaledBitmap(bm, (int) ((int) newWidth <= 0 ? 1 : newWidth), (int) ((int) newHeight <= 0 ? 1 : newHeight), false);
        } catch (Exception e) {
            return bm;
        }
    }

    public static boolean withinBounds(View v, MotionEvent ev) {
        float x = ev.getX();
        float y = ev.getY();
        return v.getX() <= x && v.getY() <= y && (v.getX() + v.getWidth()) >= x && (v.getY() + v.getHeight()) >= y;
    }

    public static boolean withinBounds(int[] vector, View v, MotionEvent ev) {
        float x = ev.getRawX();
        float y = ev.getRawY();
        return vector[0] <= x && vector[1] <= y && (vector[0] + v.getWidth()) >= x && (vector[1] + v.getHeight()) >= y;
    }

    public static int[] getWndowDimensions(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int[] displayMetric = {display.getWidth(), display.getHeight()};
        return displayMetric;
    }

    public static float dpToPx(Activity activity, int dp) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return dp * metrics.density;
    }

    public static void vibratePhone(Activity activity, int duration) {
        Vibrator v = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(duration);
    }

    public static Bitmap bitmapFromDrawable(Context context, int resID){
        return BitmapFactory.decodeResource(context.getResources(), resID);
    }

    public static float dpToPx(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }

    public static float pxToDp(float px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return dp;
    }
}
