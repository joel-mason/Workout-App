package com.company.joeliomason.projectme.Views;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.company.joeliomason.projectme.R;

import java.util.TimeZone;

import hirondelle.date4j.DateTime;

public class MainMenuActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    BootstrapPagerAdapter mSectionsPagerAdapter;
    String date;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_setup);
        try {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setLogo(R.mipmap.ic_launcher);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
            getSupportActionBar().setTitle("ProjectME");
            getSupportActionBar().setShowHideAnimationEnabled(true);
        } catch(NullPointerException n) {
            Log.v("nullPointerCaught", n.toString());
        }


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new BootstrapPagerAdapter(getResources(), getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(5000, false);
        mViewPager.post(new Runnable() {
            public void run() {
                mViewPager.setCurrentItem(5000, false);
            }
        });
        mViewPager.getAdapter().notifyDataSetChanged();
        mViewPager.setOffscreenPageLimit(1);


    }

    public void setDate(String date) {
        this.date = date;
    }





    public class BootstrapPagerAdapter extends FragmentStatePagerAdapter {

        /**
         * Create pager adapter
         *
         * @param resources
         * @param fragmentManager
         */

        public BootstrapPagerAdapter(Resources resources, FragmentManager fragmentManager) {
            super(fragmentManager);

        }

        @Override
        public int getCount() {
            return 10000;
        }

        @Override
        public int getItemPosition(Object object) {
            return FragmentStatePagerAdapter.POSITION_NONE;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            DateTime pagerdate = DateTime.now(TimeZone.getDefault());
            DateTime days = pagerdate.plusDays(position - 5000);
            String date = days.format("DD/MM/YYYY").toString();
            if(days.isSameDayAs(DateTime.now(TimeZone.getDefault()))) {
                date = "Today";
            }
            return date;
        }

        @Override
        public Fragment getItem(int position) {


            DateTime pagerdate = DateTime.now(TimeZone.getDefault());
            DateTime days = pagerdate.plusDays(position - 5000);

            Bundle bundle = new Bundle();
            bundle.putString("date", days.format("DD/MM/YYYY").toString());
            bundle.putInt("position", position);
            Log.v("date", days.format("DD/MM/YYYY").toString());
            Log.v("position", position + "");
            MainMenuView2 mainMenuView2 = new MainMenuView2();
            mainMenuView2.setArguments(bundle);
            //mainMenuView2.newInstance(position);
            return mainMenuView2;
        }



    }

}
