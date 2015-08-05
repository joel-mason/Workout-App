package com.company.joeliomason.projectme.Views;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.company.joeliomason.projectme.R;


/**
 * Created by joelmason on 08/03/2015.
 */
public class CalendarView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_picker);
        ActionBar actionBar = CalendarView.this.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle("Calendar");
        }



    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
