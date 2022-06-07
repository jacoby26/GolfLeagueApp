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


    public Round() {
    }

    public Round(long id, long teeTimeID, int score, String teeTime, String date) {
        this.id = id;
        this.teeTimeID = teeTimeID;
        this.score = score;
        this.teeTime = teeTime;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalTime getTeeTime() {
        return teeTime;
    }

    public void setTeeTime(LocalTime teeTime) {
        this.teeTime = teeTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getLeagueID() {
        return leagueID;
    }

    public void setLeagueID(long leagueID) {
        this.leagueID = leagueID;
    }

    public String getTeeTime() {
        return teeTime;
    }

    public void setTeeTime(String teeTime) {
        this.teeTime = teeTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}



