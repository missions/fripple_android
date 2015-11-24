package com.missions.fripple.activities.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.missions.fripple.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lemuel Castro on 11/24/2015.
 */

public class SampleAdapter extends RecyclerView.Adapter<SampleAdapter.InnerViewHolder> {

    List<String> names = new ArrayList<>();

    public SampleAdapter(List<String> list) {
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