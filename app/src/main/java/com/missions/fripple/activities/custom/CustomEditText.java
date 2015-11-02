package com.missions.fripple.activities.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

import com.missions.fripple.R;
import com.missions.fripple.activities.utils.CustomTypeFace;
import com.missions.fripple.activities.utils.FontFace;

/**
 * Created by User on 11/2/2015.
 */
public abstract class CustomEditText extends EditText {
    public CustomEditText(Context context) {
        super(context);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCustomFont(context, attrs);
    }

    private void setCustomFont(Context ctx, AttributeSet attrs) {
        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.CustomFont);
        String customFont = a.getString(R.styleable.CustomFont_customFont);
        setCustomFont(ctx, customFont);
        a.recycle();
    }

    public boolean setCustomFont(Context ctx, String asset) {
        Typeface tf = null;
        try {
            tf = CustomTypeFace.getTypeFace(getContext(), setFontFace().getFontFacePath());
        } catch (Exception e) {
            return false;
        }
        setTypeface(tf);
        return true;
    }

    public abstract FontFace setFontFace();

}
