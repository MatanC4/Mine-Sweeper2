package com.example.matka.minesweeper;


import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import bl.Level;
import components.LevelButton;
import data.RecordObj;
import data.ScoreTable;
import data.SharedPreferencesHandler;




public class WelcomeScreen extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener , GoogleApiClient.ConnectionCallbacks , com.google.android.gms.location.LocationListener {
    private final double BARMUDA_TRIANGLE_LONG = -73.086548;
    private final double BARMUDA_TRIANGL_LAT = 27.132481;
    private final int PERMISSION_LOCATION = 111;

    private LevelButton[] levelButtons = new LevelButton[3];
    private Button easyBtn;
    private Button medBtn;
    private Button hardBtn;
    private Button startBtn;
    private String lastLevel = null;
    private int easyBS, mediumBS, hardBS;
    private Intent intent;
    private Button testBtn;  // to be removed after test
    private Intent intent2; // to be removed after test
    private MenuItem menuItem;
    private GoogleApiClient googleApiClient;
    private ImageButton scoreBtn;
    private Location location;
    private double longitude = BARMUDA_TRIANGLE_LONG;
    private double latitude = BARMUDA_TRIANGL_LAT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //dummyWriteToPreferences();
        readNewHighScores();
        setContentView(R.layout.activity_welcome__screen);
        bindUI();
        googleApiClient = new GoogleApiClient.Builder(this).
                enableAutoManage(this,this).addConnectionCallbacks(this).addApi(LocationServices.API).build();
    }

    private void readNewHighScores() {
        ScoreTable table = SharedPreferencesHandler.getData(this);
        if(table.getHardScoreTable().size()>0)
            hardBS = table.getHardScoreTable().get(0).getScore();
        if(table.getMediumScoreTable().size()>0)
            mediumBS = table.getMediumScoreTable().get(0).getScore();
        if(table.getEasyScoreTable().size()>0)
            easyBS = table.getEasyScoreTable().get(0).getScore();
    }

    private void dummyReadFromPreferences() {
        ScoreTable table = SharedPreferencesHandler.getData(this);
        for(RecordObj ro : table.getEasyScoreTable())
            Log.d("Easy: ", ro.toString());
        for(RecordObj ro : table.getMediumScoreTable())
            Log.d("Medium: ", ro.toString());
        for(RecordObj ro : table.getHardScoreTable())
            Log.d("Hard: ", ro.toString());
    }

    private void dummyWriteToPreferences() {
        ScoreTable table = new ScoreTable();
        table.getEasyScoreTable().add(new RecordObj("a",100,"a","easy"));
        table.getEasyScoreTable().add(new RecordObj("a",99,"a","easy"));
        table.getMediumScoreTable().add(new RecordObj("a",88,"a","medium"));
        table.getMediumScoreTable().add(new RecordObj("a",98,"a","medium"));
        table.getHardScoreTable().add(new RecordObj("a",88,"a","hard"));
        table.getHardScoreTable().add(new RecordObj("a",98,"a","hard"));
        SharedPreferencesHandler.saveScoreBoard(this,table);
    }


    public void bindUI() {
        intent = new Intent(this, MineBoard.class);
        startBtn = (Button) findViewById(R.id.play_btn);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!startBtn.getText().equals("START"))
                    try{
                        startActivity(intent);
                    }catch (Exception e){

                    }

            }
        });

        intent2 = new Intent(this, ScoreBoard.class);
        if(location != null){
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        }
        intent2.putExtra("long", this.longitude);
        intent2.putExtra("lat", this.latitude);
        scoreBtn = (ImageButton) findViewById(R.id.score_table_button);
        scoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startActivity(intent2);
            }
        });
        setButtons();
        readLastPlayed();
    }

    private void setButtons() {
        easyBtn = (Button) findViewById(R.id.level1);
        easyBtn.setOnClickListener(this);
        TextView txtvieweasy = (TextView) findViewById(R.id.easyLabel);
        txtvieweasy.setText("Best Time: " + (easyBS == 0 ? "n/a" : easyBS + "s"));

        medBtn = (Button) findViewById(R.id.level2);
        medBtn.setOnClickListener(this);
        TextView txtviewmed = (TextView) findViewById(R.id.mediumLabel);
        txtviewmed.setText("Best Time: " + (mediumBS == 0 ? "n/a" : mediumBS + "s"));


        hardBtn = (Button) findViewById(R.id.level3);
        hardBtn.setOnClickListener(this);
        TextView txtviewhard = (TextView) findViewById(R.id.hardLabel);
        txtviewhard.setText("Best Time: " + (hardBS == 0 ? "n/a" : hardBS + "s"));
    }

    private void readLastPlayed() {
        SharedPreferences lastPlayed = getApplicationContext().getSharedPreferences("last_game", 0);
        lastLevel = lastPlayed.getString("last_played", "");
        if (!lastLevel.equals("")) {
            intent.putExtra("level", lastLevel);
            startBtn.setText("START\n" + lastLevel);
        }

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.level1:
                intent.putExtra("level", Level.easy.toString());
                intent.putExtra("long", this.longitude);
                intent.putExtra("lat", this.latitude);
                startBtn.setText("START\n" + Level.easy.toString());
                startBtn.setTextColor(Color.parseColor("#D7FF33"));

                break;
            case R.id.level2:
                intent.putExtra("level", Level.medium.toString());
                startBtn.setText("START\n" + Level.medium.toString());
                startBtn.setTextColor(Color.parseColor("#FFA500"));
                break;
            case R.id.level3:
                intent.putExtra("level", Level.hard.toString());
                startBtn.setText("START\n" + Level.hard.toString());
                startBtn.setTextColor(Color.parseColor("#FF3333"));
                break;
        }
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},PERMISSION_LOCATION);
        }else{
         startLocationServices();
        }

    }


    public void startLocationServices() {
        Log.d("Maps" ,"starting location services called");
        try {
            LocationRequest req = LocationRequest.create().
                    setPriority(LocationRequest.PRIORITY_LOW_POWER);
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, req,this);
        }catch (SecurityException exception){
            Toast.makeText(WelcomeScreen.this, "Cant get location until we get persmission :(", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode , @NonNull  String[] permissions,@NonNull int[] grantresults){
        super.onRequestPermissionsResult(requestCode,permissions,grantresults);
        switch (requestCode){
            case PERMISSION_LOCATION:{
               if((grantresults.length > 0) && grantresults[0] == PackageManager.PERMISSION_GRANTED)
                   startLocationServices();
               else{
                   Log.d("map","Cant get location until we get persmission :(");
                   Toast.makeText(WelcomeScreen.this, "Cant get location until we get persmission :(", Toast.LENGTH_SHORT).show();
               }

                break;
            }
        }
    }

    @Override
    protected void onStart(){
        googleApiClient.connect();
        super.onStart();

    }

    @Override
    protected void onStop(){
        googleApiClient.disconnect();
        super.onStop();
    }
    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("Map" , "Lon:" + location.getLongitude()  + location.getLatitude());
        this.location = location;
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
