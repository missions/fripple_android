package com.missions.fripple.activities.custom;

import android.content.Context;
import android.util.AttributeSet;

import com.missions.fripple.activities.utils.FontFace;

/**
 * Created by User on 11/2/2015.
 */
public class CommonButton extends CustomButton {
    public CommonButton(Context context) {
        super(context);
    }

    public CommonButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CommonButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public FontFace setFontFace() {
        return FontFace.QUAD_NORMAL;
    }
}
