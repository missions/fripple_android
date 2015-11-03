package com.missions.fripple.activities.custom;

import android.content.Context;
import android.util.AttributeSet;

import com.missions.fripple.activities.utils.FontFace;

/**
 * Created by User on 11/2/2015.
 */
public class CommonTextView extends CustomTextView {
    public CommonTextView(Context context) {
        super(context);
    }

    public CommonTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CommonTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public FontFace setFontFace() {
        return FontFace.NEOS_REGULAR;
    }
}
