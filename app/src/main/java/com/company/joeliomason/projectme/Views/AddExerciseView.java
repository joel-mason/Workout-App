package com.company.joeliomason.projectme.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.company.joeliomason.projectme.Adapters.AddExerciseAdapter;
import com.company.joeliomason.projectme.Database.CardDatabaseAdapter2;
import com.company.joeliomason.projectme.POJOs.Card;
import com.company.joeliomason.projectme.POJOs.Exercise;
import com.company.joeliomason.projectme.POJOs.Set;
import com.company.joeliomason.projectme.POJOs.User;
import com.company.joeliomason.projectme.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by joelmason on 29/03/2015.
 */
public class AddExerciseView extends AppCompatActivity {

    CardDatabaseAdapter2 mCardDatabaseAdapter2;

    TextView workoutName;
    EditText weight, reps;
    double weightCount;
    int repsCount;
    ListView list;
    Button plus, plus2, minus, minus2, update;
    String name, date;
    int type, category, count;
    Exercise ex;
    ArrayList<Set> array;
    Card card;
    private AddExerciseAdapter mAddExerciseAdapter;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_exercise_view);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name = extras.getString("ExerciseName");
            category = extras.getInt("ExerciseCategory");
            date = extras.getString("date");

        }
        mCardDatabaseAdapter2 = new CardDatabaseAdapter2(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        card = new Card(0, name, date);
        ActionBar actionBar = AddExerciseView.this.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle("Barbell Chest Press");
        }

        ex = new Exercise(name, 1, category);
        workoutName = (TextView) findViewById(R.id.name);
        weight = (EditText) findViewById(R.id.textWeight);
        reps = (EditText) findViewById(R.id.textRep);
        list = (ListView) findViewById(R.id.rowReps);
        plus = (Button) findViewById(R.id.plus);
        minus = (Button) findViewById(R.id.minus);
        plus2 = (Button) findViewById(R.id.plus2);
        minus2 = (Button) findViewById(R.id.minus2);
        update = (Button) findViewById(R.id.update);


        weightCount = 0;
        repsCount = 0;
        count = 1;

        array = new ArrayList<>();
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!weight.getText().toString().equals("")) {
                    weightCount = Double.parseDouble(weight.getText().toString());
                    weightCount = weightCount + 2.5;
                    weight.setText(String.valueOf(weightCount));
                } else {
                    weight.setText(String.valueOf(2.5));
                }
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(weightCount <= 0.0 || weight.getText().toString().equals("")) {
                    weight.setText(String.valueOf(0.0));
                } else {
                    weightCount = Double.parseDouble(weight.getText().toString());
                    weightCount = weightCount - 2.5;
                    weight.setText(String.valueOf(weightCount));
                }
            }
        });

        plus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!reps.getText().toString().equals("")) {
                    repsCount = Integer.parseInt(reps.getText().toString());
                    repsCount = repsCount + 1;
                    reps.setText(String.valueOf(repsCount));
                } else {
                    reps.setText(String.valueOf(0));
                }
            }
        });

        minus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(repsCount <= 0 || reps.getText().toString().equals("")){
                    reps.setText(String.valueOf(0));
                } else {
                    repsCount = Integer.parseInt(reps.getText().toString());
                    repsCount = repsCount - 1;
                    reps.setText(String.valueOf(repsCount));
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(weight.getText().toString().equals("") || weight.getText().toString().equals(String.valueOf(0.0))) {
                    weight.setText(String.valueOf(0.0));
                    Toast.makeText(getApplicationContext(), "You need to enter a weight!", Toast.LENGTH_SHORT).show();
                }
                else if(reps.getText().toString().equals("")|| reps.getText().toString().equals(String.valueOf(0))) {
                    reps.setText(String.valueOf(0));
                    Toast.makeText(getApplicationContext(), "You need to have at least 1 rep!", Toast.LENGTH_SHORT).show();
                } else {
                    double temp2 = Double.parseDouble(weight.getText().toString());
                    int temp3 = Integer.parseInt(reps.getText().toString());
                    Set s = new Set(0, name, temp2, temp3, date, category);
                    array.add(s);
                    mAddExerciseAdapter.notifyDataSetChanged();
                    //mCardDatabaseAdapter2.insert2(mCardDatabaseAdapter2.highestID(), name, s.getWeight(), s.getReps(), date);
                    Log.v("id", mCardDatabaseAdapter2.highestID() + "");
                    count++;
                }
            }
        });

        mAddExerciseAdapter = new AddExerciseAdapter(getApplicationContext(), R.layout.row2, array);
        list.setAdapter(mAddExerciseAdapter);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_exercise, menu);
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

        if (id == R.id.action_done) {
            Intent intent = new Intent(AddExerciseView.this, MainMenuView2.class);
            mCardDatabaseAdapter2.insert(name, date);
            for(Set s : array) {
                mCardDatabaseAdapter2.insert2(mCardDatabaseAdapter2.highestID(), name, s.getWeight(), s.getReps(), date, category);
            }
            FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
            FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users");
            String userId = mFirebaseUser.getUid();

            // pushing user to 'users' node using the userId
            mDatabase.child(userId).child("data").setValue(array);



            mCardDatabaseAdapter2.resetID();
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
