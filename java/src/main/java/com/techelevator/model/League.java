package com.techelevator.model;

public class League {

    private long leagueID;
    private String name;
    private long courseID;

    public League() { }

    public League(long leagueID, String name, long courseID) {
        this.leagueID = leagueID;
        this.name = name;
        this.courseID = courseID;
    }

    public long getLeagueID() {
        return leagueID;
    }

    public void setLeagueID(long leagueID) {
        this.leagueID = leagueID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCourseID() {
        return courseID;
    }

    public void setCourseID(long courseID) {
        this.courseID = courseID;
    }
}
