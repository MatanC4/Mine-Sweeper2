package com.example.matka.minesweeper;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.identity.intents.Address;

import android.text.InputType;
import android.widget.EditText;
import android.content.DialogInterface;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import data.RecordObj;
import data.ScoreTable;
import data.SharedPreferencesHandler;

/**
 * Created by matka on 10/12/16.
 */
public class ResultsScreen extends AppCompatActivity {

    private final double BARMUDA_TRIANGLE_LONG = -73.086548;
    private final double BARMUDA_TRIANGL_LAT = 27.132481;
    private final String UNKONOWN = "Unknown";
    private TextView title;
    private String winTitle = "Well done!";

    private ImageView smiley;
    private Button playAgainBtn;
    private String timer;
    private String name;
    private GoogleApiClient client;
    private ScoreTable table;
    private int result;
    private String level;
    private List<android.location.Address> addresses = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);
        bindUI();

    }

    public void setName(String name){
        this.name = name;
    }

    private void bindUI() {
        playAgainBtn = (Button)findViewById(R.id.play_again_btn);
        title = (TextView)findViewById(R.id.results_screen_title);
        smiley = (ImageView)findViewById(R.id.sad_smiley_icon_for_results);

        try {
            table = SharedPreferencesHandler.getData(this);
        }
        catch (Exception e){
            table = new ScoreTable();
        }

        if (getIntent().getStringExtra("status").equals("win")){
            level = getIntent().getStringExtra("level");
            result  = Integer.parseInt(getIntent().getStringExtra("result"));

            TextView finalTime = (TextView)findViewById(R.id.textViewTime);
            finalTime.setText("Your Time: " + (result/60<10?"0":"")+result/60+":"+(result%60<10?"0":"")+result%60);
            if(table.isNewRecord(level,result)){
                double longitude = getIntent().getDoubleExtra("long",500);
                double latitude = getIntent().getDoubleExtra("lat",500);
                if(longitude == 500 || latitude == 500){
                    longitude = BARMUDA_TRIANGLE_LONG;
                    latitude = BARMUDA_TRIANGL_LAT;
                }
                Geocoder geocoder;
                geocoder = new Geocoder(this, Locale.getDefault());
                try {
                    addresses = geocoder.getFromLocation(latitude, longitude, 1);
                } catch (IOException e) {
                    addresses = new ArrayList<>();
                }
                registerUserWithNewRecord(this);


/*                if(addresses.size()>0)
                    table.newRecord(new RecordObj(name, result,addresses.get(0).getAddressLine(0), level));
                else
                    table.newRecord(new RecordObj(name, result,UNKONOWN, level));*/
            }
            title.setText(winTitle);
            Drawable victorySmile = getResources().getDrawable(R.drawable.victory_smiley,getTheme());
            smiley.setImageDrawable(victorySmile);
        }

        timer =  getIntent().getStringExtra("results");
        playAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = anotherGameIntent();
                intent.putExtra("result", timer);
                startActivity(intent);
                finish();
            }


        });

    }

    public void saveRecord(String name){
        String address;
        if(addresses.size()>0)
            address = addresses.get(0).getAddressLine(0);
        else{
            address = UNKONOWN;
        }
        table.newRecord(new RecordObj(name, result,address, level));
        SharedPreferencesHandler.saveScoreBoard(this,table);
    }

    private void registerUserWithNewRecord(final ResultsScreen screen) {
        final EditText input;
        String name;

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("New Record!\nEnter your name for hall of fame" );


        input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);



        builder.setPositiveButton("Save Record", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (input.getText().toString().isEmpty()) {
                    Toast.makeText(ResultsScreen.this, "Player will be saved as Unknown", Toast.LENGTH_SHORT).show();
                    screen.saveRecord(UNKONOWN);
                }
                else{
                    screen.saveRecord(input.getText().toString());
                }
            }
        });

        builder.show();
    }

    private Intent anotherGameIntent() {
        Intent intent  = new Intent(this,WelcomeScreen.class);
        return intent;
    }
}
