package com.company.joeliomason.projectme.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.company.joeliomason.projectme.POJOs.Set;
import com.company.joeliomason.projectme.R;

import java.util.ArrayList;

/**
 * Created by joelmason on 01/04/2015.
 */
public class EditCardioAdapter extends ArrayAdapter {


    Context mContext;
    int mLayoutResourceId;
    ArrayList<Set> mData;


    public EditCardioAdapter(Context context, int resource, ArrayList<Set> data) {
        super(context, resource, data);
        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.mData = data;
    }

    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        PlaceHolder holder = null;
        //inflate the layout for a single row

        if(row == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(mLayoutResourceId, parent, false);

            holder = new PlaceHolder();

            //holder.set = (TextView) row.findViewById(R.id.rowSet);
            holder.weight = (TextView) row.findViewById(R.id.rowWeight);
            holder.reps = (TextView) row.findViewById(R.id.rowReps);

            row.setTag(holder);

        } else {
            //otherwise use an existing one
            holder = (PlaceHolder) row.getTag();
            Log.v("Positon", "i " + position);

        }
        //getting the data from the data array
        Set place = mData.get(position);

        //setup and reuse the same onclicklistener for each row
        //holder.nameView.setOnClickListener(popupListener);
        //holder.set.setTag(position);


        //setting the view to reflect the data we need to display

        holder.weight.setText(String.valueOf(place.getReps()));
        holder.reps.setText(String.valueOf(place.getWeight()));
        holder.reps.setTextColor(Color.GRAY);
        holder.weight.setTextColor(Color.GRAY);

        //returning the row
        return row;



    }



    private static class PlaceHolder {

        //TextView set;
        TextView weight;
        TextView reps;

    }
}
