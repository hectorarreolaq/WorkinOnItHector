package com.example.hector.workinonitfinal;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hector on 11/29/16.
 */

public class MyAdapter extends BaseAdapter {

    ArrayList<Habit> habits;
    Activity activity;

    public MyAdapter(ArrayList<Habit> habits, Activity activity) {
        this.habits = habits;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return habits.size();
    }

    @Override
    public Object getItem(int position) {
        return habits.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView== null){
            convertView=activity.getLayoutInflater().inflate(R.layout.row, null);
        }

        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView description = (TextView) convertView.findViewById(R.id.description);
        //TextView status = (TextView) convertView.findViewById(R.id.status);

        name.setText(habits.get(position).getName());
        description.setText(habits.get(position).getDescription());
        //status.setText(habits.get(position).getStatus());

        return convertView;
    }

}
