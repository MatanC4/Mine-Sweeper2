package com.example.matka.minesweeper;

import android.content.Context;
import android.support.v4.app.*;

import com.google.android.gms.maps.MapFragment;

/**
 * Created by matka on 13/01/17.
 */

public class CollectionPagerAdapter extends FragmentStatePagerAdapter {
    private MyScoresMap map;
    private ScoresListView scoresListView;

    public CollectionPagerAdapter(FragmentManager fm , MyScoresMap map , ScoresListView scoresListView) {
        super(fm);
        this.map = map;
        this.scoresListView = scoresListView;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return this.scoresListView;
            case 1:
                return this.map;
            default:
                return null;
        }
    }

    @Override
    public int getCount () {
        return 2;
    }

    @Override
    public CharSequence getPageTitle ( int position){
        if (position == 0)
            return ("Score Table");
        else
            return ("Map View");
    }
}


