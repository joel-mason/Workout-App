package com.company.joeliomason.projectme.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.company.joeliomason.projectme.POJOs.Set;
import com.company.joeliomason.projectme.R;

import java.util.ArrayList;

/**
 * Created by joelmason on 01/04/2015.
 */
public class ExerciseHistoryAdapter extends ArrayAdapter {


    Context mContext;
    int mLayoutResourceId;
    ArrayList<Set> mData;
    PlaceHolder holder;
    ListView lv;

    public ExerciseHistoryAdapter(Context context, int resource, ArrayList<Set> data, ListView lv) {
        super(context, resource, data);
        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.mData = data;
        this.lv = lv;
    }

    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View row = convertView;
        holder = null;
        //inflate the layout for a single row

        if(row == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(mLayoutResourceId, parent, false);

            holder = new PlaceHolder();

            holder.weight = (TextView) row.findViewById(R.id.time);
            holder.reps = (TextView) row.findViewById(R.id.distance);
            holder.date = (TextView) row.findViewById(R.id.date);
            row.setTag(holder);

        } else {
            //otherwise use an existing one
            holder = (PlaceHolder) row.getTag();
            Log.v("Position", "i " + position);

        }
        //getting the data from the data array
        Set place = mData.get(position);

        //setting the view to reflect the data we need to display

        holder.weight.setText(String.valueOf(place.getWeight()));
        holder.reps.setText(String.valueOf(place.getReps()));
        holder.date.setText(String.valueOf(place.getDate()));

        // Update color based on selected state

        row.setBackgroundColor(
                lv.isItemChecked(position)
                        ? Color.rgb(255, 152, 0) // selected color
                        : Color.rgb(221, 221, 221) // normal color
        );

        //returning the row
        return row;
    }




    private static class PlaceHolder {

        TextView weight;
        TextView reps;
        TextView date;

    }
}

