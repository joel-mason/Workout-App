package com.company.joeliomason.projectme.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CalendarView;

import com.company.joeliomason.projectme.R;


/**
 * Created by joelmason on 08/03/2015.
 */
public class CalendarActivityView extends AppCompatActivity {

    private  static final String TAG = "CalendarActivity";
    private CalendarView mCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_picker);
        mCalendarView = findViewById(R.id.calendarView);
        ActionBar actionBar = CalendarActivityView.this.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle("Calendar");
        }

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView CalendarView, int year, int month, int dayOfMonth) {
                String d, m, y;
                if(dayOfMonth < 10) {
                    d = "0" + (dayOfMonth);
                } else {
                    d = dayOfMonth + "";
                }
                if(month < 9) {
                    m = "0" + (month+1);
                } else {
                    m = (month+1) + "";
                }
                y = year + "";
                String date = d + "/" + m + "/"+ y ;
                Log.d(TAG, "onSelectedDayChange: yyyy/mm/dd:" + date);
                Intent intent = new Intent(CalendarActivityView.this,MainMenuActivity.class);
                intent.putExtra("date",date);
                startActivity(intent);

            }
        });

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
