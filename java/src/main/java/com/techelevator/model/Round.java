package com.techelevator.model;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Round {

    private long id;
    private long teeTimeID;
    private int score;
    private String date;
    private String teeTime;
    private int leagueId;

    public Round() {
    }

    public Round(long id, long teeTimeID, int score, String date, String teeTime) {
        this.id = id;
        this.teeTimeID = teeTimeID;
        this.score = score;
        this.date = date;
        this.teeTime = teeTime;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTeeTime() {
        return teeTime;
    }

    public void setTeeTime(String teeTime) {
        this.teeTime = teeTime;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }
}



