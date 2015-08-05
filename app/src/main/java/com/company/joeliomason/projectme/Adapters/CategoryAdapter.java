package com.company.joeliomason.projectme.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.company.joeliomason.projectme.POJOs.Category;

import java.util.ArrayList;

/**
 * Created by joelmason on 29/03/2015.
 */
public class CategoryAdapter extends ArrayAdapter {

    Context mContext;
    int mLayoutResourceId;
    ArrayList<Category> mData;


    public CategoryAdapter(Context context, int resource, ArrayList<Category> data) {
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
        LayoutInflater inflater = LayoutInflater.from(mContext);
        row = inflater.inflate(mLayoutResourceId, parent, false);

        holder = new PlaceHolder();

        holder.nameView = (TextView) row.findViewById(android.R.id.text1);

        row.setTag(holder);
        //getting the data from the data array
        Category place = mData.get(position);
        Log.v("place", place.getName());

        //setup and reuse the same onclicklistener for each row
        //holder.nameView.setOnClickListener(popupListener);


        //setting the view to reflect the data we need to display
        holder.nameView.setText(place.getName());
        holder.nameView.setTextColor(Color.BLACK);

        //returning the row
        return row;



    }



    private static class PlaceHolder {

        TextView nameView;

    }
}
