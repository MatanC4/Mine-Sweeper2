package com.example.matka.minesweeper;

import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.ArrayList;
import java.util.List;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.ActionBar;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import data.RecordObj;
import data.ScoreTable;
import data.SharedPreferencesHandler;


public class ScoreBoard extends FragmentActivity implements ScoresListView.OnFragmentInteractionListener   {

    //private MyScoresMap mp;
    private GoogleMap myMap;
    private MyScoresMap  myScoreMap;
    private ScoresListView scoreListview;

    DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
    ViewPager mViewPager;
    private Button changeMode;
    private boolean viewMap = false;
    private FragmentManager fm;
    private FragmentTransaction transaction;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();
        scoreListview = ScoresListView.newInstance();
        myScoreMap = MyScoresMap.newInstance();
        transaction.replace(R.id.frame_layout, scoreListview);
        transaction.commit();

        bindUi();


        //mDemoCollectionPagerAdapter =  new DemoCollectionPagerAdapter(getSupportFragmentManager(),myScoreMap);

        //MyScoresMap myScoreMap = (MyScoresMap) mDemoCollectionPagerAdapter.getItem(1);


    }

    private void bindUi() {

        //mViewPager = (ViewPager) findViewById(R.id.pager);
        //mViewPager.setAdapter(mDemoCollectionPagerAdapter);
        changeMode = (Button) findViewById(R.id.change_mode);
        changeMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transaction = fm.beginTransaction();
                if (viewMap){
                    if (scoreListview == null){
                        scoreListview = ScoresListView.newInstance();
                    }
                    transaction.replace(R.id.frame_layout, scoreListview);
                    transaction.commit();
                }else{
                    if (myScoreMap==null){
                        myScoreMap = MyScoresMap.newInstance();
                    }

                    transaction.replace(R.id.frame_layout, myScoreMap);
                    transaction.commit();

                }
                viewMap = !viewMap;
               // transaction.addToBackStack(null);
                //transaction.commitAllowingStateLoss();
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

   /*@Override
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


    }*/


    public void addMarkerToMap(int lat, int lon){
        if(myMap == null)
            return;
        LatLng sydney = new LatLng(lat,lon);
        myMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        myMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }

}






