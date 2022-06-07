package com.techelevator.model;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Round {

    private long teeTimeID;
    private String date;
    private String teeTime;
    private long leagueID;
    private String leagueName;
    private int score;
    private long courseId;

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public Round() {
    }

    public Round(long leagueID, long teeTimeID, int score, String date, String teeTime) {
        this.leagueID = leagueID;
        this.teeTimeID = teeTimeID;
        this.score = score;
        this.date = date;
        this.teeTime = teeTime;
    }

    public long getLeagueID() {
        return this.leagueID;
    }

    public void setLeagueID(long id) {
        this.leagueID = id;
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

//    public int getLeagueId() {
//        return leagueId;
//    }
//
//    public void setLeagueId(int leagueId) {
//        this.leagueId = leagueId;
//    }
}



