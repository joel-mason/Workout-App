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

import com.company.joeliomason.projectme.Adapters.CategoryAdapter;
import com.company.joeliomason.projectme.Database.WorkoutDatabaseAdapter;
import com.company.joeliomason.projectme.POJOs.Category;
import com.company.joeliomason.projectme.R;

import java.util.ArrayList;

/**
 * Created by joelmason on 07/03/2015.
 */
public class CategoryListView extends AppCompatActivity {
    private static WorkoutDatabaseAdapter dbAdapter;
    private static ArrayList<Category> values;
    private ListView mListView;
    private CategoryAdapter mCategoryAdapter;
    static String date;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            date = extras.getString("date");
            Log.v("date", date);
        }

        setContentView(R.layout.category_view2);
        mListView = (ListView) findViewById(R.id.listview);

        ActionBar actionBar = CategoryListView.this.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle("Categories");
        }

        dbAdapter = new WorkoutDatabaseAdapter(this);
        values = dbAdapter.getAllCategories();
        Log.d("Item", values.toString());

        mCategoryAdapter = new CategoryAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, values);
        mListView.setAdapter(mCategoryAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View view, int i, long l) {
                Intent intent = new Intent(CategoryListView.this, ExerciseView.class);
                intent.putExtra("workoutID", i+1);
                intent.putExtra("WorkoutName", values.get(i).getName());
                intent.putExtra("date", date);
                Log.d("Got:", String.valueOf(i+1));
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
