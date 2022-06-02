package com.techelevator.model;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Round {

    private long id;
    private long teeTimeID;
    private int score;


    public Round() {
    }

    public Round(long id, long teeTimeID, int score) {
        this.id = id;
        this.teeTimeID = teeTimeID;
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTeeTimeID() {
        return teeTimeID;
    }

    public void setTeeTimeID(long teeTimeID) {
        this.teeTimeID = teeTimeID;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}



