package com.example.matka.minesweeper;

/**
 * Created by matka on 14/01/17.
 */
public class RecordObj {

    private String userName;
    private String score;
    private String location;
    private String level;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public RecordObj(String userName, String score, String location) {

        this.userName = userName;
        this.score = score;
        this.location = location;
    }
}
