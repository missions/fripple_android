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
public class CardAdapterNewEstablishment extends RecyclerView.Adapter {

    List<String> list = new ArrayList<>();

    public CardAdapterNewEstablishment(List<String> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CardViewAdapter(LayoutInflater.from(parent.getContext()).inflate(R.layout.new_establishment_card, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CardViewAdapter extends RecyclerView.ViewHolder {

        public CardViewAdapter(View itemView) {
            super(itemView);
        }
    }
}
