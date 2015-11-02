package com.missions.fripple.activities.custom;

import android.content.Context;
import android.util.AttributeSet;

import com.missions.fripple.activities.utils.FontFace;

/**
 * Created by User on 11/2/2015.
 */
public class CommonEditText extends CustomEditText {
    public CommonEditText(Context context) {
        super(context);
    }

    public CommonEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CommonEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public FontFace setFontFace() {
        return FontFace.QUAD_NORMAL;
    }
}
