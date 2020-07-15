package com.example.alcoparty;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created


    // Build a Constructor and assign the passed Values to appropriate values in the class

    public ViewPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {
// пошел нахуй пидорас ебаный
        switch (position) {// if the position is 0 we are returning the First tab
            case 0:
                FragmentMain fragMain = new FragmentMain();
                return fragMain;

            case 1:           // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab

                FragmentTable fragTable = new FragmentTable();
                return fragTable;

        }
        return null;
    }

    // This method return the titles for the Print Screen

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs ol

    @Override
    public int getCount() {
        return NumbOfTabs;
        //return Alesha ti pidoras
    }
}