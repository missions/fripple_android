package com.missions.fripple.activities.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.missions.fripple.R;

import java.util.List;

/**
 * Created by Lemuel Castro on 11/25/2015.
 */
public class DrawerAdapter extends BaseAdapter {

    public static final int HOME = 0;
    public static final int FIND = 1;
    public static final int MAP = 2;
    public static final int PROFILE = 3;

    private List<String> icons;
    private int selectedPos = 0;
    private Context context;
    private DrawerItemClickListener listener;

    public interface DrawerItemClickListener{
        void onDrawerItemClick(int position);
    }

    public DrawerAdapter(List<String> iconMap, DrawerItemClickListener listener) {
        this.icons = iconMap;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return icons.size();
    }

    @Override
    public String getItem(int position) {
        return icons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (context == null) {
            context = parent.getContext();
        }
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_view_layout, parent, false);
        String key = icons.get(position);
        if (selectedPos == position) {
            setUpSelected(view, key);
        } else {
            setUpNotSelected(view, key);
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPos != position) {
                    selectedPos = position;
                    listener.onDrawerItemClick(position);
                }
            }
        });
        return view;
    }

    public void setUpSelected(View view, String key) {
        TextView title = ((TextView) view.findViewById(R.id.title));
        title.setText(key);
        title.setTextColor(view.getContext().getResources().getColor(R.color.black));

        int iconResID = context.getResources().getIdentifier("icon_" + key.toLowerCase() + "_on", "drawable", view.getContext().getPackageName());
        ((ImageView) view.findViewById(R.id.icon)).setImageDrawable(context.getResources().getDrawable(iconResID));
        view.findViewById(R.id.selected_indicator).setBackgroundColor(view.getContext().getResources().getColor(R.color.Orange));
    }

    public void setUpNotSelected(View view, String key) {
        TextView title = ((TextView) view.findViewById(R.id.title));
        title.setText(key);
        title.setTextColor(view.getContext().getResources().getColor(R.color.gray));

        int iconResID = context.getResources().getIdentifier("icon_" + key.toLowerCase() + "_off", "drawable", view.getContext().getPackageName());
        ((ImageView) view.findViewById(R.id.icon)).setImageDrawable(context.getResources().getDrawable(iconResID));
        view.findViewById(R.id.selected_indicator).setBackgroundColor(view.getContext().getResources().getColor(R.color.Gray));
    }
}
