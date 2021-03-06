package com.example.matka.minesweeper;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
//import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v4.app.Fragment;

import com.google.android.gms.maps.*;

import java.util.ArrayList;

import data.RecordObj;
import data.ScoreTable;
import data.SharedPreferencesHandler;


public class ScoresListView extends Fragment {

    ArrayList<String> recordList;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ScoresListView() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scores_list_view, container, false);
        ScoreTable table;
        try{
            table = SharedPreferencesHandler.getData(getContext());
        }
        catch(Exception e){
            table = new ScoreTable();
        }
        recordList = new ArrayList<>();
        ArrayList<RecordObj> recordObjs;
        recordObjs = table.getScoreTable();
        for (RecordObj ro : recordObjs){
            try{
                recordList.add(ro.toString());
            }
            catch (Exception e){};

        }
        ListView listView = (ListView) view.findViewById(R.id.listViewScores);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,
                recordList);
        listView.setAdapter(arrayAdapter);
        return view;
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public static ScoresListView newInstance() {
        ScoresListView fragment = new ScoresListView();
        return fragment;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
