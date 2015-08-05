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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.company.joeliomason.projectme.Adapters.EditExerciseAdapter;
import com.company.joeliomason.projectme.Database.CardDatabaseAdapter2;
import com.company.joeliomason.projectme.POJOs.Card;
import com.company.joeliomason.projectme.POJOs.Set;
import com.company.joeliomason.projectme.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joelmason on 17/04/2015.
 */
public class EditExerciseView extends AppCompatActivity {

    CardDatabaseAdapter2 mCardDatabaseAdapter2;

    TextView workoutName;
    EditText weight, reps;
    double weightCount;
    int repsCount, pos = 0, category;
    ListView list;
    Button plus, plus2, minus, minus2, update, edit, delete;
    String name;
    Card card;
    ArrayList<Set> array = new ArrayList<>();
    List<Set> foo;
    private EditExerciseAdapter mAddExerciseAdapter;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_exercise_view);
        foo = new ArrayList<>();
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                card = (Card) extras.getSerializable("Card");
                foo = card.getSet();
                for (Set temp : foo) {
                    array.add(temp);
                    category = temp.getCategory();
                }
                id = card.getId();
                name = card.getName();
            }
        mCardDatabaseAdapter2 = new CardDatabaseAdapter2(this);


        ActionBar actionBar = EditExerciseView.this.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(card.getName());
        }

        //Log.v("Exercise Recieved", ex.toString());
        workoutName = (TextView) findViewById(R.id.name);
        weight = (EditText) findViewById(R.id.textWeight);
        reps = (EditText) findViewById(R.id.textRep);
        list = (ListView) findViewById(R.id.rowReps);
        plus = (Button) findViewById(R.id.plus);
        minus = (Button) findViewById(R.id.minus);
        plus2 = (Button) findViewById(R.id.plus2);
        minus2 = (Button) findViewById(R.id.minus2);
        update = (Button) findViewById(R.id.update);
        edit = (Button) findViewById(R.id.edit);
        edit.setVisibility(View.GONE);
        delete = (Button) findViewById(R.id.delete);
        delete.setVisibility(View.GONE);

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
                if (weightCount <= 0.0 || weight.getText().toString().equals("")) {
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
                if (repsCount <= 0 || reps.getText().toString().equals("")) {
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

                if (weight.getText().toString().equals("")) {
                    weight.setText(String.valueOf(0.0));
                    Toast.makeText(getApplicationContext(), "You need to enter a weight!", Toast.LENGTH_SHORT).show();
                } else if (reps.getText().toString().equals("") || reps.getText().toString().equals(String.valueOf(0))) {
                    reps.setText(String.valueOf(0));
                    Toast.makeText(getApplicationContext(), "You need to have at least 1 rep!", Toast.LENGTH_SHORT).show();
                } else {
                    double temp2 = Double.parseDouble(weight.getText().toString());
                    int temp3 = Integer.parseInt(reps.getText().toString());
                    Set s = new Set(0, name, temp2, temp3, card.getDate(), category);
                    mCardDatabaseAdapter2.insert2(id, name, s.getWeight(), s.getReps(), card.getDate(), s.getCategory());
                    array.add(s);
                    mAddExerciseAdapter.notifyDataSetChanged();
                    Log.v("id", pos + "");

                }
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                array.get(pos).setWeight(Double.valueOf(weight.getText().toString()));
                array.get(pos).setReps(Integer.valueOf(reps.getText().toString()));
                update.setVisibility(View.VISIBLE);
                edit.setVisibility(View.GONE);
                delete.setVisibility(View.GONE);
                mAddExerciseAdapter.notifyDataSetChanged();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update.setVisibility(View.VISIBLE);
                edit.setVisibility(View.GONE);
                delete.setVisibility(View.GONE);
                mCardDatabaseAdapter2.deleteEntry(String.valueOf(array.get(pos).getId()));
                //mCardDatabaseAdapter2.deleteEntry(String.valueOf(id), String.valueOf(array.get(pos).getName()), String.valueOf(array.get(pos).getWeight()), String.valueOf(array.get(pos).getReps()));
                Log.v(String.valueOf(id), array.get(pos).getName() + String.valueOf(array.get(pos).getWeight()) + String.valueOf(array.get(pos).getReps()));
                array.remove(pos);
                mAddExerciseAdapter.notifyDataSetChanged();
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                weight.setText(array.get(position).getWeight() + "");
                reps.setText(array.get(position).getReps() + "");
                update.setVisibility(View.INVISIBLE);
                edit.setVisibility(View.VISIBLE);
                delete.setVisibility(View.VISIBLE);
                pos = position;
                Toast.makeText(view.getContext(), "something" + pos, Toast.LENGTH_SHORT).show();
            }
        });

        mAddExerciseAdapter = new EditExerciseAdapter(getApplicationContext(), R.layout.row2, array);
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
            Intent intent = new Intent(EditExerciseView.this, MainMenuActivity.class);
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
