package com.example.matka.minesweeper;

import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.ActionBar;
import android.widget.Toast;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import components.MapReadyListener;
import data.RecordObj;
import data.ScoreTable;
import data.SharedPreferencesHandler;


public class ScoreBoard extends FragmentActivity implements ScoresListView.OnFragmentInteractionListener, MapReadyListener {
    @Override
    public void mapReadyNotification() {
        LatLng latLng = new LatLng(getIntent().getDoubleExtra("lat",100),getIntent().getDoubleExtra("long",5));
        myScoreMap.markUserLocation(latLng);
    }

    private MyScoresMap  myScoreMap;
    private ScoresListView scoreListview;

    DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
    ViewPager mViewPager;
    private FragmentManager fm;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        fm = getSupportFragmentManager();
        scoreListview = ScoresListView.newInstance();
        myScoreMap = MyScoresMap.newInstance();
        myScoreMap.setListener(this);
        bindUi();
    }
    private void bindUi() {
        mDemoCollectionPagerAdapter =  new DemoCollectionPagerAdapter(getSupportFragmentManager(),myScoreMap, scoreListview);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}






