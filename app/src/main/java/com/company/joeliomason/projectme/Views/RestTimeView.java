package com.company.joeliomason.projectme.Views;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.company.joeliomason.projectme.R;

/**
 * Created by joelmason on 23/04/2015.
 */
public class RestTimeView extends AppCompatActivity {

    private CountDownTimer mCountDownTimer;
    public boolean timerHasStarted = false;
    Button start, stop;
    TextView timer;
    String date, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countdown_timerr);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            date = extras.getString("date");
            name = extras.getString("name");
        }

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.hide();
        }

        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        timer = (TextView) findViewById(R.id.timer);
        //mCountDownTimer = new MyCountDownTimer(0, 0);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!timerHasStarted) {
                    timerHasStarted = true;
                    mCountDownTimer = new MyCountDownTimer((Long.valueOf(timer.getText().toString()) * 1000), 1000);
                    mCountDownTimer.start();
                } else {
                    Toast.makeText(getApplicationContext(), "Timer has already started!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(timerHasStarted) {
                    timerHasStarted = false;
                    mCountDownTimer = new MyCountDownTimer(Long.valueOf(timer.getText().toString()), 1000);
                    mCountDownTimer.cancel();
                    Intent intent  = new Intent(RestTimeView.this, AddExerciseActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("date", date);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Timer isn't running!", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }
        @Override
        public void onFinish() {
            Intent intent  = new Intent(RestTimeView.this, AddExerciseActivity.class);
            startActivity(intent);
        }
        @Override
        public void onTick(long millisUntilFinished) {

            timer.setText(String.valueOf((millisUntilFinished / 1000)));

        }

    }
}
