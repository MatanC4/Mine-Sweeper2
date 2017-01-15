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


public class ScoreBoard extends FragmentActivity implements ScoresListView.OnFragmentInteractionListener , OnMapReadyCallback  {

    //private MyScoresMap mp;
    private GoogleMap myMap;
    Fragment mp;
    DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
    ViewPager mViewPager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

       // mp = getSupportFragmentManager()
             //.findFragmentById(R.id.map);

        // mapFragment = (SupportMapFragment) getSupportFragmentManager()
          //      .findFragmentById(R.id.map);
        //mapFragment.getMapAsync(this);
        // ViewPager and its adapters use support library
        // fragments, so use getSupportFragmentManager.


        bindUi();


    }

    private void bindUi() {
        mDemoCollectionPagerAdapter =  new DemoCollectionPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);
        addMarkerToMap(-34, 151);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

   @Override
    public void onMapReady(GoogleMap googleMap) {
        myMap = googleMap;


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






