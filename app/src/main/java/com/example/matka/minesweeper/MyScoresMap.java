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
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by matka on 13/01/17.
 */
public class MyScoresMap extends Fragment implements OnMapReadyCallback  {
    //public static final String ARG_OBJECT = "object";
    private GoogleMap myMap;
    public MyScoresMap() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        return view;


    }

   /* private void initMap(GoogleMap myMap) {
        LatLng sydney = new LatLng(-34, 151);
        myMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        myMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }*/


    @Override
    public void onMapReady(GoogleMap googleMap) {
        myMap = googleMap;

    }
}
//Integer.toString(args.getInt(ARG_OBJECT))
