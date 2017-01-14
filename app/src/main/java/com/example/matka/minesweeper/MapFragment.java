package com.example.matka.minesweeper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by matka on 13/01/17.
 */
public class MapFragment extends Fragment {
    //public static final String ARG_OBJECT = "object";
    String text;
    public MapFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

       // ListView listView = (ListView) view.findViewById(R.id.listViewScores);
        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,
          //      menuItems);
        //listView.setAdapter(arrayAdapter);


        // Inflate the layout for this fragment
        return view;
        //View rootView = inflater.inflate(R.layout.fragment_item_list, container, false);
        //Bundle args = getArguments();

        //((TextView) rootView.findViewById(android.R.id.text1)).setText("vrrvrvrrwvrw");
        //return rootView;
    }
}
//Integer.toString(args.getInt(ARG_OBJECT))
