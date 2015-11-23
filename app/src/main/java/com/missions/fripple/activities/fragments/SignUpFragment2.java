package com.missions.fripple.activities.fragments;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.missions.fripple.R;
import com.missions.fripple.activities.activities.Main;
import com.missions.fripple.activities.custom.CommonTextView;
import com.missions.fripple.activities.custom.CustomFragment;
import com.missions.fripple.activities.singletons.FacebookSession;
import com.missions.fripple.activities.utils.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lemuel Castro on 11/20/2015.
 */

//// TODO: 11/20/2015  error handling
public class SignUpFragment2 extends CustomFragment {

    @Bind(R.id.textView_perkup)
    TextView perkUp;

    private static final int NAME = 0;
    private static final int EMAIL = 1;
    private static final int PASSWORD = 2;
    private static final int CONFIRM_PASSWORD = 3;

    private EditText editTextInfo;
    private ImageView btnDone;
    private TextSwitcher title/*, perkUp*/;

    private int doneBtnWitdh, doneBtnHeight;
    private int phaseCount = NAME;

    private String info[] = new String[3];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signup, container, false);
        ButterKnife.bind(this, view);

        editTextInfo = (EditText) view.findViewById(R.id.edittext_infos);
        editTextInfo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().equals("") && btnDone.getVisibility() != View.VISIBLE) {
                    popUpDoneBtn();
                } else if (s.toString().trim().equals("") && btnDone.getVisibility() == View.VISIBLE) {
                    popDownDoneBtn();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editTextInfo.requestFocus();

        btnDone = (ImageView) view.findViewById(R.id.button_done);
        btnDone.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                doneBtnWitdh = btnDone.getWidth();
                doneBtnHeight = btnDone.getHeight();
                btnDone.setPivotX(doneBtnWitdh / 2);
                btnDone.setPivotY(doneBtnHeight / 2);
                btnDone.setVisibility(View.GONE);
                btnDone.getViewTreeObserver().removeOnPreDrawListener(this);
                return true;
            }
        });

        title = (TextSwitcher) view.findViewById(R.id.textSwitcher_info_title);
        title.setInAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.abc_fade_in));
        title.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.abc_fade_out));
        title.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                CommonTextView commonTextView = new CommonTextView(getContext());
                commonTextView.setText("Hey!");
                commonTextView.setTextColor(getResources().getColor(R.color.white));
                commonTextView.setTypeface(null, Typeface.BOLD);
                commonTextView.setGravity(Gravity.CENTER);
                commonTextView.setTextSize(Utils.dpToPx(20, getContext()));
                return commonTextView;
            }
        });

        /*perkUp = (TextSwitcher) view.findViewById(R.id.textView_perkup);
        perkUp.setInAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.abc_fade_in));
        perkUp.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.abc_fade_out));

        perkUp.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                CommonTextView commonTextView = new CommonTextView(getContext());
                commonTextView.setText("Tell us something about you!!");
                commonTextView.setGravity(Gravity.CENTER);
                commonTextView.setTextColor(getResources().getColor(R.color.white));
                commonTextView.setTextSize(Utils.dpToPx(5, getContext()));
                return commonTextView;
            }
        });
*/
        return view;
    }

    private void popUpDoneBtn() {
        Log.i("lem", "pop up");
        ObjectAnimator popX = ObjectAnimator.ofFloat(btnDone, "ScaleX", 0.0f, 1.0f);
        ObjectAnimator popY = ObjectAnimator.ofFloat(btnDone, "ScaleY", 0.0f, 1.0f);
        AnimatorSet pop = new AnimatorSet();
        pop.playTogether(popX, popY);
        pop.setDuration(100);
        pop.setInterpolator(new AccelerateInterpolator());
        pop.start();
        btnDone.setVisibility(View.VISIBLE);
    }

    private void popDownDoneBtn() {
        Log.i("lem", "pop down");
        ObjectAnimator popX = ObjectAnimator.ofFloat(btnDone, "ScaleX", 1.0f, 0.0f);
        ObjectAnimator popY = ObjectAnimator.ofFloat(btnDone, "ScaleY", 1.0f, 0.0f);
        AnimatorSet pop = new AnimatorSet();
        pop.playTogether(popX, popY);
        pop.setDuration(100);
        pop.setInterpolator(new AccelerateInterpolator());
        pop.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                btnDone.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        pop.start();
    }

    @OnClick(R.id.button_done)
    final void doneBtnPressed() {
        setUpView();
        switch (phaseCount) {
            case NAME:
                info[NAME] = editTextInfo.getText().toString();
                phaseCount = EMAIL;
                break;
            case EMAIL:
                info[EMAIL] = editTextInfo.getText().toString();
                phaseCount = PASSWORD;
                break;
            case PASSWORD:
                info[PASSWORD] = editTextInfo.getText().toString();
                phaseCount = CONFIRM_PASSWORD;
                break;
        }
        editTextInfo.setText("");
    }

    private void setUpView() {
        switch (phaseCount) {
            case NAME:
                String userName = editTextInfo.getText().toString();
                userName = userName.split(" ")[0];
                title.setText("Hi " + userName + "!");
                editTextInfo.setHint("Email");
                perkUp.setText("A bit more information if you dont mind...");
                break;
            case EMAIL:
                perkUp.setText("To secure things up...");
                editTextInfo.setHint("Password");
                editTextInfo.setInputType(InputType.TYPE_CLASS_TEXT);
                break;
            case PASSWORD:
                perkUp.setText("Just to be sure you know what you're doing..");
                editTextInfo.setHint("Confirm Password");
                editTextInfo.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                break;
        }
    }

    private void resetView() {
        editTextInfo.setText(info[phaseCount]);
        switch (phaseCount) {
            case NAME:
                title.setText("Hey!");
                editTextInfo.setHint("Name");
                perkUp.setText("Tell us something about you!");
                break;
            case EMAIL:
                String userName = info[NAME].split(" ")[0];
                title.setText("Hi " + userName + "!");
                editTextInfo.setHint("Email");
                editTextInfo.setInputType(InputType.TYPE_CLASS_TEXT);
                perkUp.setText("A bit more information if you dont mind...");
                break;
            case PASSWORD:
                perkUp.setText("To secure things up...");
                editTextInfo.setHint("Password");
                editTextInfo.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                break;
        }
    }

    @Override
    public boolean overrideOnBackPressed() {
        if (phaseCount == NAME) {
            getActivity().finish();
        } else {
            phaseCount--;
            resetView();
        }
        return true;
    }

    @OnClick(R.id.login)
    public final void startMain(){
        FacebookSession.loginFacebook(getActivity(), new FacebookSession.SessionListener() {
            @Override
            public void onProfileChanged() {
                startActivity(new Intent(getActivity(), Main.class));
                getActivity().finish();
            }

            @Override
            public void onAccessTokenChange() {
                Log.i("lem", "access changed");
            }
        });
    }
}
