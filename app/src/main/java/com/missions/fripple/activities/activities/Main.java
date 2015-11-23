package com.missions.fripple.activities.activities;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.missions.fripple.R;
import com.missions.fripple.activities.custom.CustomDrawerActivity;
import com.missions.fripple.activities.singletons.FacebookSession;
import com.missions.fripple.activities.utils.CircleTransform;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lemuel Castro on 11/23/2015.
 */
public class Main extends CustomDrawerActivity {

    private RecyclerView recyclerView;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private FacebookSession fbSession;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fbSession = FacebookSession.getInstance();

        List<String> names = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            names.add("");
        }

        recyclerView = (RecyclerView) findViewById(R.id.rvToDoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new SampleViewHolder(names));

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(fbSession.getProfile().getName());

        Glide.with(this).load(fbSession.getProfile().getProfilePictureUri(200, 200)).transform(new CircleTransform(this)).into((ImageView) findViewById(R.id.profile_pic));
    }

    public class SampleViewHolder extends RecyclerView.Adapter<SampleViewHolder.InnerViewHolder> {

        List<String> names = new ArrayList<>();

        public SampleViewHolder(List<String> list) {
            this.names = list;
        }

        @Override
        public InnerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new InnerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.sample, parent, false));
        }

        @Override
        public void onBindViewHolder(InnerViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return names.size();
        }

        public class InnerViewHolder extends RecyclerView.ViewHolder {

            public InnerViewHolder(View itemView) {
                super(itemView);
            }
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.main;
    }
}
