package com.company.joeliomason.projectme.Adapters;

import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;

import com.company.joeliomason.projectme.Views.EditExerciseFragment1;
import com.company.joeliomason.projectme.Views.EditExerciseFragment2;

/**
 * Created by joelmason on 18/04/2015.
 */
public class TabsAdapter2 extends FragmentPagerAdapter {

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        switch(position) {
            case 0: return new EditExerciseFragment1();
            case 1: return new EditExerciseFragment2();
        }
        return null;
    }

    public String[] tabNames = {"Edit Exercise", "Exercise History"};

    public TabsAdapter2(android.support.v4.app.FragmentManager fragmentManager) {
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
