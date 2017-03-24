package com.bogoslovov.kaloyan.beer;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by kaloqn on 3/21/17.
 */

public class TabsAdapter extends FragmentPagerAdapter {
    private final int PAGE_COUNT = 2;
    private String tabTitles[];
    private Context context;

    public TabsAdapter(FragmentManager fm, Context con) {
        super(fm);
        context = con;
        tabTitles = context.getResources().getStringArray(R.array.tab_names);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                return new FirstFragment();
            case 1 :
                return new SecondFragment();
        }

        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
