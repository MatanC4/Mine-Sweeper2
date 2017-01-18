package com.example.matka.minesweeper;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import components.MapReadyListener;
import data.RecordObj;
import data.ScoreTable;
import data.SharedPreferencesHandler;

/**
 * Created by matka on 13/01/17.
 */
public class MyScoresMap extends Fragment implements OnMapReadyCallback {

    private final double BARMUDA_TRIANGLE_LONG = -70.086548;
    private final double BARMUDA_TRIANGL_LAT = 23.132481;
    private GoogleMap myMap;
    private static FragmentManager fm;
    private  static FragmentTransaction transaction;
    private MarkerOptions userMarker;
    private MapReadyListener listener;

    public MyScoresMap() {

    }

    public void setListener(MapReadyListener listener){
        this.listener = listener;
    }

    public static MyScoresMap newInstance()  {
        MyScoresMap fragment = new MyScoresMap();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        SupportMapFragment supportmapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.map_fragment_map_layout);
        supportmapFragment.getMapAsync(this);

        return view;


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        myMap = googleMap;
        ScoreTable st;
        try{
            st = SharedPreferencesHandler.getData(getContext());
        }
        catch (Exception e){
            st = new ScoreTable();
        }
        ArrayList<RecordObj> scores = st.getScoreTable();
        for(RecordObj r :scores ){
            Geocoder geocoder = new Geocoder(getContext(),Locale.getDefault());
            List<Address> addresses;
            double latitude;
            double longitude;
            try{
                addresses = geocoder.getFromLocationName(r.getLocation(), 1);
                if(addresses.size() > 0) {
                    latitude = addresses.get(0).getLatitude();
                    longitude = addresses.get(0).getLongitude();
                    myMap.addMarker(new MarkerOptions().position(new LatLng(latitude,longitude))).setTitle(addresses.get(0).getAddressLine(0));
                }
                else{
                }
            }catch (IOException e) {
                myMap.addMarker(new MarkerOptions().position(new LatLng(BARMUDA_TRIANGL_LAT,BARMUDA_TRIANGLE_LONG))).setTitle("Unknown Address");
            }
        }
        listener.mapReadyNotification();
    }

    public void markUserLocation(LatLng latLng){
        userMarker = new MarkerOptions().position(latLng).title("Current Location");
        myMap.addMarker(userMarker);
        myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,5));
    }
}
