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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by joelmason on 18/04/2015.
 */
public class EditExerciseFragment2 extends Fragment {

    ExerciseHistoryAdapter mExerciseHistoryAdapter;
    static AddExerciseActivity mAddExerciseActivity;
    static ArrayList<Set> sets;
    static ArrayList<Set> selected;
    static ArrayList<Integer> positions;
    static ArrayList<String> dates;
    //static String name;
    static ListView lv;
    Card c;
    int x;
    FirebaseAuth mFirebaseAuth;
    FirebaseUser mFirebaseUser;
    DatabaseReference mDatabase;
    String userId;
    Card card;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.exercise_history, container, false);
        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            card = (Card) extras.getSerializable("Card");
        }
        sets = new ArrayList<>();
        selected = new ArrayList<>();
        positions = new ArrayList<>();
        dates = new ArrayList<>();

        //firebase adds
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        userId = mFirebaseUser.getUid();
        //get the reference to /exercises
        mDatabase = FirebaseDatabase.getInstance().getReference("users/" + userId + "/history/" + card.getName());
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String tempDate = "";
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Set mSet = new Set();
                    tempDate = dataSnapshot1.child("date").getValue().toString();
                    mSet.setId(0);
                    mSet.setCategory(Integer.parseInt(dataSnapshot1.child("category").getValue().toString()));
                    mSet.setDate(tempDate);
                    mSet.setName(dataSnapshot1.child("name").getValue().toString());
                    mSet.setReps(Integer.parseInt(dataSnapshot1.child("reps").getValue().toString()));
                    mSet.setWeight(Double.parseDouble(dataSnapshot1.child("weight").getValue().toString()));
                    sets.add(mSet);
                }
                if(!tempDate.equals("")) {
                    if(!dates.contains(tempDate)) {
                        dates.add(tempDate);
                    }
                }

                mExerciseHistoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                mExerciseHistoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                mExerciseHistoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                mExerciseHistoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                mExerciseHistoryAdapter.notifyDataSetChanged();
            }
        });
        //end firebase adds
        Log.v("array", sets.toString());

        lv = (ListView) rootView.findViewById(R.id.history_list);
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!selected.contains(sets.get(position))) {
                    lv.setItemChecked(position, true);
                    view.setBackgroundColor(Color.rgb(255, 152, 0));
                    selected.add(sets.get(position));
                    positions.add(position);
                } else {
                    lv.setItemChecked(position, false);
                    view.setBackgroundColor(Color.rgb(221, 221, 221));
                    selected.remove(sets.get(position));
                    for(int i = 0; i < positions.size(); i++) {
                        if(positions.get(i) == position) {
                            positions.remove(i);
                            break;
                        }
                    }
                }
            }
        });



        Button add = (Button) rootView.findViewById(R.id.add_button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((EditExerciseActivity)getActivity()).setSets(selected);
                for(Integer integer : positions) {
                    lv.setItemChecked(integer, false);
                }
                selected.clear();
                positions.clear();
                ((EditExerciseActivity) getActivity()).changeTab(0); //go to FirstFragment)

            }
        });

        mExerciseHistoryAdapter= new ExerciseHistoryAdapter(getActivity(), R.layout.row_exercise, sets, lv);
        lv.setAdapter(mExerciseHistoryAdapter);
        mExerciseHistoryAdapter.notifyDataSetChanged();

        return rootView;
    }
}
