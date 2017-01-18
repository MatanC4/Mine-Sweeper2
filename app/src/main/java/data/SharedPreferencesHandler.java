package data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.Collections;

/**
 * Created by Daniel_m on 14/01/2017.
 */

public class SharedPreferencesHandler {

    public static String DB_NAME = "High_Scores";
    public static String DB_TABLE = "table";

    public static void saveScoreBoard(Context context, ScoreTable scoreTable){
        SharedPreferences sharedPref = context.getApplicationContext().getSharedPreferences(DB_NAME, 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(scoreTable);
        editor.putString(DB_TABLE, json);
        editor.apply();
    }


    public static ScoreTable getData(Context context){
        SharedPreferences scoresDB = context.getApplicationContext().getSharedPreferences(DB_NAME, 0);
        String json = scoresDB.getString(DB_TABLE,null);
        Gson gson = new Gson();
        ScoreTable st = gson.fromJson(json, ScoreTable.class);
        st.sortTables();
        return st;
    }
}



