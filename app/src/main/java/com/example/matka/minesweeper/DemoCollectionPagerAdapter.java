package com.example.matka.minesweeper;

import android.content.Context;
import android.support.v4.app.*;

import com.google.android.gms.maps.MapFragment;

/**
 * Created by matka on 13/01/17.
 */

    public class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {
        private MyScoresMap context;

        public DemoCollectionPagerAdapter(FragmentManager fm , MyScoresMap context) {
            super(fm);
            this.context = context;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    ScoresListView tab1 = new ScoresListView();
                    return tab1;
                case 1:
                    //MyScoresMap myScoremMap = (MyScoresMap) context.getSupportFragmentManager().findFragmentById(R.id.frame_layout);
                    //if (myScoremMap==null){
                     // myScoremMap = MyScoresMap.newInstance();
                    //context.getSupportFragmentManager().beginTransaction().add(R.id.frame_layout,myScoremMap).commit();
                    //}
                    return context;
                default:
                    return null;
            }

           /* Fragment fragment = new MyScoresMap();
            Bundle args = new Bundle();
            // Our object is just an integer :-P
            ScoresListView slv = new ScoresListView();
            args.putInt("object", i+1);


            fragment.setArguments(args);
            return fragment;*/
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
                    return ("MyScoresMap View");
            }
        }


