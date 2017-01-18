package com.example.matka.minesweeper;

import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentManager;

import com.google.android.gms.maps.model.LatLng;

import components.MapReadyListener;


public class ScoreBoard extends FragmentActivity implements ScoresListView.OnFragmentInteractionListener, MapReadyListener {
    @Override
    public void mapReadyNotification() {
        LatLng latLng = new LatLng(getIntent().getDoubleExtra("lat",200),getIntent().getDoubleExtra("long",200));
        myScoreMap.markUserLocation(latLng);
    }

    private MyScoresMap  myScoreMap;
    private ScoresListView scoreListview;

    CollectionPagerAdapter mDemoCollectionPagerAdapter;
    ViewPager mViewPager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        scoreListview = ScoresListView.newInstance();
        myScoreMap = MyScoresMap.newInstance();
        myScoreMap.setListener(this);
        bindUi();
    }
    private void bindUi() {
        mDemoCollectionPagerAdapter =  new CollectionPagerAdapter(getSupportFragmentManager(),myScoreMap, scoreListview);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}






