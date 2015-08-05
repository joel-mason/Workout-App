package com.company.joeliomason.projectme.Views;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.company.joeliomason.projectme.Adapters.TabsAdapter;
import com.company.joeliomason.projectme.R;

/**
 * Created by joelmason on 18/04/2015.
 */
public class AddExercise extends FragmentActivity implements ActionBar.TabListener{

    private ViewPager viewPager;
    private TabsAdapter tabsAdapter;
    private ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        tabsAdapter = new TabsAdapter(getSupportFragmentManager());

        viewPager.setAdapter(tabsAdapter);

        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        //add the tabs
        for(String tabName : tabsAdapter.getTabNames()) {
            ActionBar.Tab aTab = actionBar.newTab().setText(tabName).setTabListener(this);
            actionBar.addTab(aTab);
        }

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

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
            Intent intent = new Intent(AddExercise.this, MainMenuView2.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}