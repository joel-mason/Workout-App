package com.company.joeliomason.projectme.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.company.joeliomason.projectme.Adapters.CardAdapter;
import com.company.joeliomason.projectme.Database.CardDatabaseAdapter2;
import com.company.joeliomason.projectme.POJOs.Card;
import com.company.joeliomason.projectme.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JoelioMason on 09/02/15.
 */


public class MainMenuView2 extends Fragment {

    CardDatabaseAdapter2 mCardDatabaseAdapter2;

    List<Card> cards = new ArrayList<>();
    List<Card> cardPerDate = new ArrayList<>();
    //ArrayList<Set> selected = new ArrayList<>();
    RecyclerView recList;
    CardAdapter ca;
    LinearLayoutManager llm;
    CardView cd;
    String date;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_menu2, container, false);
        setHasOptionsMenu(true);
        date = this.getArguments().getString("date");
        Log.v("date", date);
        mCardDatabaseAdapter2 = new CardDatabaseAdapter2(getActivity());

        Log.v("meh", mCardDatabaseAdapter2.getAllInfo2(date).toString());
        cardPerDate = mCardDatabaseAdapter2.getAllInfo2(date);
        for(Card c : cardPerDate) {
            if(c.getDate().equals(date)) {
                cards.add(c);
            }
        }
        recList = (RecyclerView) rootView.findViewById(R.id.cardList);
        cd = (CardView) rootView.findViewById(R.id.card_view);
        recList.setHasFixedSize(true);
        llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        ca = new CardAdapter(getActivity(), cards);
        recList.setAdapter(ca);
        ca.notifyDataSetChanged();
        //((MainMenuActivity) getActivity()).setDate(date);
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

        if(id == R.id.action_add) {
            Intent intent = new Intent(getActivity(), CategoryListView.class);
            intent.putExtra("date", date);
            startActivity(intent);
        }

        if(id == R.id.action_cal) {
            Intent intent = new Intent(getActivity(), CalendarView.class);
            startActivity(intent);
        }

        if(id == R.id.action_copy) {
            Toast.makeText(getActivity(), "Copy has been clicked", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }




}
