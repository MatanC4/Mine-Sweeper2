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
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import data.RecordObj;
import data.ScoreTable;
import data.SharedPreferencesHandler;


public class ScoreBoard extends FragmentActivity implements ScoresListView.OnFragmentInteractionListener , OnMapReadyCallback  {

    //private MyScoresMap mp;
    private GoogleMap myMap;
    Fragment mp;
    DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
    ViewPager mViewPager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);

        MyScoresMap myScoreMap = (MyScoresMap)getSupportFragmentManager().findFragmentById(R.id.frame_layout);
        if (myScoreMap==null){
            myScoreMap = MyScoresMap.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.frame_layout,myScoreMap).commit();
        }

        bindUi();
        mDemoCollectionPagerAdapter =  new DemoCollectionPagerAdapter(getSupportFragmentManager(),myScoreMap);

        //MyScoresMap myScoreMap = (MyScoresMap) mDemoCollectionPagerAdapter.getItem(1);


    }

    private void bindUi() {

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

   @Override
    public void onMapReady(GoogleMap googleMap) {
        myMap = googleMap;
       ScoreTable st = SharedPreferencesHandler.getData(this);
       ArrayList<RecordObj> scores = st.getScoreTable();
       for(RecordObj r :scores ){
           r.getLocation();
       }

       LatLng sydney = new LatLng(-34,151);
       myMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
       myMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


    }


    public void addMarkerToMap(int lat, int lon){
        if(myMap == null)
            return;
        LatLng sydney = new LatLng(lat,lon);
        myMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        myMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }

}






