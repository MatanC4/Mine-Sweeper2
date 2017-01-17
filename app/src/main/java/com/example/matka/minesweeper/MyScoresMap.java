package com.example.matka.minesweeper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import data.RecordObj;
import data.ScoreTable;
import data.SharedPreferencesHandler;

/**
 * Created by matka on 13/01/17.
 */
public class MyScoresMap extends Fragment implements OnMapReadyCallback  {
    private GoogleMap myMap;
    public MyScoresMap() {

    }

    public static MyScoresMap newInstance()  {
        MyScoresMap fragment = new MyScoresMap();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
               .findFragmentById(R.id.map_fragment_map_layout);
        mapFragment.getMapAsync(this);

        return view;


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        myMap = googleMap;
        ScoreTable st = SharedPreferencesHandler.getData(getContext());
        ArrayList<RecordObj> scores = st.getScoreTable();
        for(RecordObj r :scores ){
            r.getLocation();
        }

        LatLng sydney = new LatLng(-34,151);
        myMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        myMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


    }
}
//Integer.toString(args.getInt(ARG_OBJECT))
