package com.company.joeliomason.projectme.Views;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.company.joeliomason.projectme.Adapters.ExerciseHistoryAdapter;
import com.company.joeliomason.projectme.Database.CardDatabaseAdapter2;
import com.company.joeliomason.projectme.POJOs.Card;
import com.company.joeliomason.projectme.POJOs.Set;
import com.company.joeliomason.projectme.R;

import java.util.ArrayList;

/**
 * Created by joelmason on 18/04/2015.
 */
public class AddCardioFragment2 extends Fragment {

    CardDatabaseAdapter2 mCardDatabaseAdapter;
    ExerciseHistoryAdapter mExerciseHistoryAdapter;
    static AddExerciseActivity mAddExerciseActivity;
    static ArrayList<Set> sets = new ArrayList<>();
    static ArrayList<Set> selected = new ArrayList<>();
    static String name;
    static ListView lv;
    Card c;
    int x;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.exercise_history, container, false);
        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            name = extras.getString("ExerciseName");
        }
        mAddExerciseActivity = new AddExerciseActivity();
        mCardDatabaseAdapter = new CardDatabaseAdapter2(getActivity());
        sets = mCardDatabaseAdapter.getAllSetsWithName(name);
        Log.v("array", sets.toString());

        lv = (ListView) rootView.findViewById(R.id.history_list);
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!selected.contains(sets.get(position))) {
                    lv.setItemChecked(position, true);
                    view.setBackgroundColor(Color.rgb(255, 152, 0));
                    Toast.makeText(getActivity(), "Item Selected", Toast.LENGTH_SHORT).show();
                    selected.add(sets.get(position));
                } else {
                    lv.setItemChecked(position, false);
                    view.setBackgroundColor(Color.rgb(221, 221, 221));
                    selected.remove(sets.get(position));
                    Toast.makeText(getActivity(), "Item Unselected", Toast.LENGTH_SHORT).show();
                }
            }
        });



        Button add = (Button) rootView.findViewById(R.id.add_button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        ((AddExerciseActivity)getActivity()).setSets(selected);

                        Toast.makeText(getActivity(), "items have been added successfully", Toast.LENGTH_SHORT).show();
            }
        });

        mExerciseHistoryAdapter= new ExerciseHistoryAdapter(getActivity(), R.layout.row_cardio, sets, lv);
        lv.setAdapter(mExerciseHistoryAdapter);

        return rootView;
    }
}
