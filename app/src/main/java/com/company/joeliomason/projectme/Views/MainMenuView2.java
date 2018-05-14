package com.company.joeliomason.projectme.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.company.joeliomason.projectme.Adapters.CardAdapter;
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
import java.util.List;

/**
 * Created by JoelioMason on 09/02/15.
 */


public class MainMenuView2 extends Fragment {

    List<Card> cards = new ArrayList<>();
    RecyclerView recList;
    CardAdapter ca;
    LinearLayoutManager llm;
    CardView cd;
    String date;
    FirebaseAuth mFirebaseAuth;
    FirebaseUser mFirebaseUser;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_menu2, container, false);
        setHasOptionsMenu(true);
        date = this.getArguments().getString("date");
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        String strUid = mFirebaseUser.getUid();

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users/" + strUid);
        /**
        cardPerDate = mCardDatabaseAdapter2.getAllInfo2(date);
        for(Card c : cardPerDate) {
            if(c.getDate().equals(date)) {
                cards.add(c);
            }
        }
         **/
        String noSlashDate = "";
        for(char curr : date.toCharArray()) {
            if(curr != '/') {
                noSlashDate+=curr;
            }
        }
        recList = (RecyclerView) rootView.findViewById(R.id.cardList);
        cd = (CardView) rootView.findViewById(R.id.card_view);
        recList.setHasFixedSize(true);
        llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        //((MainMenuActivity) getActivity()).setDate(date);
        if(mDatabase.child(noSlashDate) != null) {
            mDatabase.child(noSlashDate).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    if(dataSnapshot.getChildrenCount() > 0) {
                        Card temp = new Card(dataSnapshot.getKey().toString(), dataSnapshot.child("0/name").getValue().toString(), dataSnapshot.child("0/date").getValue().toString());
                        for(DataSnapshot ds : dataSnapshot.getChildren()) {
                            Set mSet = new Set();
                            mSet.setId(0);
                            mSet.setCategory(Integer.parseInt(ds.child("category").getValue().toString()));
                            mSet.setDate(ds.child("date").getValue().toString());
                            mSet.setName(ds.child("name").getValue().toString());
                            mSet.setReps(Integer.parseInt(ds.child("reps").getValue().toString()));
                            mSet.setWeight(Double.parseDouble(ds.child("weight").getValue().toString()));
                            temp.addSet(mSet);
                        }
                        cards.add(temp);
                        ca.notifyDataSetChanged();
                    }
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    ca.notifyDataSetChanged();
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    ca.notifyDataSetChanged();
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                    ca.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    ca.notifyDataSetChanged();
                }
            });
        }


        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CategoryListView.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });

        ca = new CardAdapter(getActivity(), cards);
        recList.setAdapter(ca);
        ca.notifyDataSetChanged();

        return rootView;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
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

        if(id == R.id.action_cal) {
            Intent intent = new Intent(getActivity(), CalendarActivityView.class);
            startActivity(intent);
        }

        if(id == R.id.action_copy) {
            Toast.makeText(getActivity(), "Copy has been clicked", Toast.LENGTH_SHORT).show();
        }

        if(id == R.id.action_logout) {
            mFirebaseAuth.signOut();
            getActivity().finish();
            Intent intent = new Intent(getActivity(), SplashScreenView.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }




}
