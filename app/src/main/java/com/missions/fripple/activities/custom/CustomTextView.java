package com.missions.fripple.activities.custom;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.missions.fripple.R;
import com.missions.fripple.activities.utils.CustomTypeFace;
import com.missions.fripple.activities.utils.FontFace;

/**
 * Created by User on 11/2/2015.
 */
public abstract class CustomTextView extends TextView {

    private boolean adjust;
    private Activity activity;
    private View parentView;
    private boolean isAdjusted;
    private static final float DEFAULT_SIZE = -10.f;
    private float originalTextSize = -10.0f;
    private static final float ZERO_SIZE = 0;
    private static final float MINIMUM_TEXT_SIZE = 8.0f;
    private TextSizeChangeListener listener;
    private String originalText;

    public interface TextSizeChangeListener{
        public void sizeComputed(String text, float size);
        public boolean hasComputed(String text);
        public float getComputedSize(String text);
    }

    /*Adjustable layouts must define singleLine to true
    * and enable ellipsize to end, this will not make the layout scale up
    * when changes is font is made
    * */
    public void setAdjust(Activity activity, boolean adjust, TextSizeChangeListener listener) {
        this.listener = listener;
        this.adjust = adjust;
        this.activity = activity;
    }

    private void setObserver() {
        this.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (CustomTextView.this.getLayout() == null) {
                    return;
                }
                int end = CustomTextView.this.getText().length() - CustomTextView.this.getLayout().getEllipsisCount(0);

                if (!isAdjusted && (originalTextSize == -10.0f || originalTextSize == 0) && activity != null) {
                    originalTextSize = pxToDp(CustomTextView.this.getTextSize());
                }

                if(listener!=null && listener.hasComputed(originalText)){
                    CustomTextView.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    CustomTextView.this.setTextSize(TypedValue.COMPLEX_UNIT_DIP, pxToDp(listener.getComputedSize(originalText)));
                    return;
                }

                if (end == CustomTextView.this.getText().length() || !adjust) {
                    isAdjusted = false;
                    CustomTextView.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    CustomTextView.this.setVisibility(View.VISIBLE);

                    if(listener!=null){
                        listener.sizeComputed(originalText, CustomTextView.this.getTextSize());
                    }
                } else {
                    isAdjusted = true;
                    if(pxToDp(CustomTextView.this.getTextSize()) == MINIMUM_TEXT_SIZE) {
                        CustomTextView.this.setVisibility(View.VISIBLE);
                        if(listener!=null){
                            listener.sizeComputed(originalText, CustomTextView.this.getTextSize());
                        }
                        return;
                    } else {
                        CustomTextView.this.setVisibility(View.INVISIBLE);
                        CustomTextView.this.setTextSize(TypedValue.COMPLEX_UNIT_DIP, pxToDp(CustomTextView.this.getTextSize()) - 1.0f);
                    }
                }
            }
        });
    }

    public float pxToDp(float px) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return px/metrics.density;
    }

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
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

    @Override
    public void setText(CharSequence text, BufferType type) {
        if(!(originalTextSize == DEFAULT_SIZE || originalTextSize == ZERO_SIZE)) {
            CustomTextView.this.setTextSize(TypedValue.COMPLEX_UNIT_DIP, originalTextSize);
        }
        originalText = text.toString();
        if(listener!=null && listener.hasComputed(originalText)){
            CustomTextView.this.setTextSize(TypedValue.COMPLEX_UNIT_DIP, pxToDp(listener.getComputedSize(originalText)));
        }else{
            setObserver();
        }
        super.setText(text, type);
    }
}
