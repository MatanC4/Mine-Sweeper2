package com.example.matka.minesweeper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by matka on 10/12/16.
 */
public class ResultsScreen extends AppCompatActivity {

    private TextView title;
    private String winTitle = "Well done!";

    private ImageView smiley;
    private Button playAgainBtn;
    private String timer;
    private GoogleApiClient client;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);
        bindUI();

    }

    private void bindUI() {
        playAgainBtn = (Button)findViewById(R.id.play_again_btn);
        title = (TextView)findViewById(R.id.results_screen_title);
        smiley = (ImageView)findViewById(R.id.sad_smiley_icon_for_results);

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("Scores", 0);

        if (getIntent().getStringExtra("status").equals("win")){

            String level = getIntent().getStringExtra("level");
            int bestScore = sharedPref.getInt(level, 0);
            int result  = Integer.parseInt(getIntent().getStringExtra("result"));
            TextView finalTime = (TextView)findViewById(R.id.textViewTime);
            finalTime.setText("Your Time: " + (result/60<10?"0":"")+result/60+":"+(result%60<10?"0":"")+result%60);
            if(result<bestScore || bestScore==0){
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt(level, result);
                editor.apply();
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

    private Intent anotherGameIntent() {
        Intent intent  = new Intent(this,WelcomeScreen.class);
        return intent;
    }



}
