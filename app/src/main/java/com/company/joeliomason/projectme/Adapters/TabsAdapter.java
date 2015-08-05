package com.company.joeliomason.projectme.Adapters;

import android.support.v4.app.FragmentPagerAdapter;

import com.company.joeliomason.projectme.Views.FirstFragment;
import com.company.joeliomason.projectme.Views.SecondFragment;

/**
 * Created by joelmason on 18/04/2015.
 */
public class TabsAdapter extends FragmentPagerAdapter {

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        switch(position) {
            case 0: return new FirstFragment();
            case 1: return new SecondFragment();
        }
        return null;
    }

    public String[] tabNames = {"Add Exercise", "Exercise History"};

    public TabsAdapter(android.support.v4.app.FragmentManager fragmentManager) {
        super(fragmentManager);
    }


    @Override
    public int getCount() {
        return tabNames.length;
    }

    public String[] getTabNames() {
        return tabNames;
    }

    public void setTabNames(String[] tabNames) {
        this.tabNames = tabNames;
    }
}
