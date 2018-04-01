package com.example.auliaheryanov.auliaheryanov_1202150063_modul6;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Aulia Heryanov on 01/04/2018.
 */

public class PageAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PageAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new TabMenu();

            case 1:
                return new TabProfile();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}