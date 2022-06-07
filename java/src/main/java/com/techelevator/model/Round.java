package com.techelevator.model;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Round {

    private long id;
    private LocalTime teeTime;
    private LocalDate date;
    private long leagueID;


    public Round() {
    }

    public Round(long id, LocalTime teeTime, LocalDate date, long leagueID) {
        this.id = id;
        this.teeTime = teeTime;
        this.date = date;
        this.leagueID = leagueID;
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
}



