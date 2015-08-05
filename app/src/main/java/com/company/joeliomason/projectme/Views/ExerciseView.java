package com.company.joeliomason.projectme.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.company.joeliomason.projectme.Adapters.ExerciseAdapter;
import com.company.joeliomason.projectme.Database.WorkoutDatabaseAdapter;
import com.company.joeliomason.projectme.POJOs.Exercise;
import com.company.joeliomason.projectme.R;

import java.util.ArrayList;

/**
 * Created by joelmason on 09/03/2015.
 */
public class ExerciseView extends AppCompatActivity {

    private WorkoutDatabaseAdapter dbAdapter;
    private static ArrayList<Exercise> values;
    private ListView mListView;
    private ExerciseAdapter mExerciseAdapter;
    private static int x;
    private static String y;
    static String date;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbAdapter = new WorkoutDatabaseAdapter(this);
        values = new ArrayList<>();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            x = extras.getInt("workoutID");
            y = extras.getString("WorkoutName");
            date = extras.getString("date");
            Log.v("WorkoutID", String.valueOf(x));
            Log.v("date", date);
        }

        setContentView(R.layout.exercise_view);

        ActionBar actionBar = ExerciseView.this.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(y);
        }
        mListView = (ListView) findViewById(R.id.listview);
        dbAdapter = new WorkoutDatabaseAdapter(this);
        values = dbAdapter.getExercisePerCategory1(x);

        mExerciseAdapter = new ExerciseAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, values);
        mListView.setAdapter(mExerciseAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View view, int i, long l) {
                Intent intent;
                if(values.get(i).getCategory() == 7) {
                    intent = new Intent(ExerciseView.this, AddCardioActivity.class);
                    intent.putExtra("ExerciseName", values.get(i).getName());
                    intent.putExtra("ExerciseType", values.get(i).getType());
                    intent.putExtra("ExerciseCategory", values.get(i).getCategory());
                    intent.putExtra("date", date);
                } else {
                    intent = new Intent(ExerciseView.this, AddExerciseActivity.class);
                    intent.putExtra("ExerciseName", values.get(i).getName());
                    intent.putExtra("ExerciseType", values.get(i).getType());
                    intent.putExtra("ExerciseCategory", values.get(i).getCategory());
                    intent.putExtra("date", date);
                }
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        dbAdapter.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        dbAdapter.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_search) {
            Toast.makeText(this, "Search has been clicked!", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

}
