package com.example.matka.minesweeper;

import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.ArrayList;
import java.util.List;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.TextView;
import android.app.ActionBar;

import com.google.android.gms.maps.*;


public class ScoreBoard extends FragmentActivity implements ScoresListView.OnFragmentInteractionListener {

    public Fragment myMapFragment;
    private GoogleMap myMap;
    DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
    ViewPager mViewPager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);

        // ViewPager and its adapters use support library
        // fragments, so use getSupportFragmentManager.
        bindUi();


    }

    private void bindUi() {
        mDemoCollectionPagerAdapter =  new DemoCollectionPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);
        myMapFragment = mDemoCollectionPagerAdapter.getItem(1);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}






