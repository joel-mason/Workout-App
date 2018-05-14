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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.company.joeliomason.projectme.Adapters.ExerciseAdapter;
import com.company.joeliomason.projectme.Database.WorkoutDatabaseAdapter;
import com.company.joeliomason.projectme.POJOs.Exercise;
import com.company.joeliomason.projectme.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by joelmason on 09/03/2015.
 */
public class ExerciseView extends AppCompatActivity {

    private WorkoutDatabaseAdapter dbAdapter;
    private static ArrayList<String> values;
    private ListView mListView;
    private ExerciseAdapter mExerciseAdapter;
    private static int categoryId;
    private static String categoryName;
    static String date;
    private DatabaseReference mDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        values = new ArrayList<>();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            categoryId = extras.getInt("workoutID");
            categoryName = extras.getString("WorkoutName");
            date = extras.getString("date");
        }

        setContentView(R.layout.exercise_view);

        ActionBar actionBar = ExerciseView.this.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(categoryName);
        }
        mListView = (ListView) findViewById(R.id.listview);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
        mListView.setAdapter(adapter);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("exercises").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    if(dataSnapshot1.getKey().equals("category " + categoryId)) {
                        for(DataSnapshot ds : dataSnapshot1.getChildren()) {
                            adapter.add((String) ds.getValue());
                            values.add((String) ds.getValue());
                        }
                    }
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                adapter.remove((String) dataSnapshot.child("title").getValue());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        /**
        dbAdapter = new WorkoutDatabaseAdapter(this);
        values = dbAdapter.getExercisePerCategory1(x);

        mExerciseAdapter = new ExerciseAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, values);
        mListView.setAdapter(mExerciseAdapter);
         **/

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View view, int i, long l) {
                Intent intent;
                intent = new Intent(ExerciseView.this, AddExerciseActivity.class);
                intent.putExtra("ExerciseName", values.get(i));
                intent.putExtra("ExerciseCategory", categoryId);
                intent.putExtra("date", date);
                finish();
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
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
