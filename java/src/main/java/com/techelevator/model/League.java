package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class League {

    private long leagueID;
    private String name;
    private long courseID;
    @JsonProperty("course")
    private GolfCourse golfCourse;
    private long leagueOrganizerID;

    public List<LeaderboardRow> getLeaderboardTable() {
        return leaderboardTable;
    }

    public void setLeaderboardTable(List<LeaderboardRow> leaderboardTable) {
        this.leaderboardTable = leaderboardTable;
    }

    private List<LeaderboardRow> leaderboardTable = new ArrayList<>();

    public League() { }

    public League(long leagueID, String name, long courseID, GolfCourse golfCourse, long leagueOrganizerID) {
        this.leagueID = leagueID;
        this.name = name;
        this.courseID = courseID;
        this.golfCourse = golfCourse;
        this.leagueOrganizerID = leagueOrganizerID;
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

    public GolfCourse getGolfCourse() {
        return golfCourse;
    }

    public void setGolfCourse(GolfCourse golfCourse) {
        this.golfCourse = golfCourse;
    }

    public long getLeagueOrganizerID() {
        return leagueOrganizerID;
    }

    public void setLeagueOrganizerID(long leagueOrganizerID) {
        this.leagueOrganizerID = leagueOrganizerID;
    }
}
