package com.example.matka.minesweeper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;

/**
 * Created by matka on 13/01/17.
 */
public class MapFragment extends Fragment {
    //public static final String ARG_OBJECT = "object";
    public GoogleMap googleMap;
    public MapFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        return view;

    }




}
//Integer.toString(args.getInt(ARG_OBJECT))
