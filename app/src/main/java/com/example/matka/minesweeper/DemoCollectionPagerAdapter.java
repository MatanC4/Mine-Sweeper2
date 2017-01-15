package com.example.matka.minesweeper;

import android.support.v4.app.*;

/**
 * Created by matka on 13/01/17.
 */

    public class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {
        public DemoCollectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    ScoresListView tab1 = new ScoresListView();
                    return tab1;
                case 1:
                    MyScoresMap tab2 = new MyScoresMap();
                    return tab2;
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


