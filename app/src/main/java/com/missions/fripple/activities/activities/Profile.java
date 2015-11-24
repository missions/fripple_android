package com.missions.fripple.activities.activities;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.missions.fripple.R;
import com.missions.fripple.activities.adapters.SampleAdapter;
import com.missions.fripple.activities.custom.CustomAppCompatActivity;
import com.missions.fripple.activities.singletons.FacebookSession;
import com.missions.fripple.activities.utils.CircleTransform;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lemuel Castro on 11/23/2015.
 */
public class Profile extends CustomAppCompatActivity {

    private RecyclerView recyclerView;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private FacebookSession fbSession;
    private AppBarLayout appBarLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        fbSession = FacebookSession.getInstance();

        List<String> names = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            names.add("");
        }

        recyclerView = (RecyclerView) findViewById(R.id.rvToDoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new SampleAdapter(names));

        appBarLayout = (AppBarLayout)findViewById(R.id.appbar);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(fbSession.getProfile().getName());

        Glide.with(this).load(fbSession.getProfile().getProfilePictureUri(200, 200)).transform(new CircleTransform(this)).into((ImageView) findViewById(R.id.profile_pic));

       /* collapsingToolbarLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                appBarLayout.invalidate();
                Log.i("lem", "height " + collapsingToolbarLayout.getHeight());
                AppBarLayout.LayoutParams p = (AppBarLayout.LayoutParams) collapsingToolbarLayout.getLayoutParams();
                p.setScrollFlags(0);
                collapsingToolbarLayout.setLayoutParams(p);
                collapsingToolbarLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        appBarLayout.setExpanded(false, false);*/
    }
}
