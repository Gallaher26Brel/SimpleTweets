package com.codepath.apps.simpletweets.fragments;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TweetsPagerAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[] {"Home", "Mentions"};
    private Context context;

    public TweetsPagerAdapter (FragmentManager fm, Context context){
        super (fm);
        this.context = context;
    }

    // return total number of fragments

    @Override
    public int getCount() {
        return 2;
    }

    // return the fragment to use depending on the position

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new HomeTimelineFragment();
        }
        else if (position == 1){
            return  new MentionsTimelineFragment();
        }
        else
            return null;
    }

    // return fragment title

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        // generate title basted on position
        return tabTitles[position];
//        return super.getPageTitle(position);
    }
}
