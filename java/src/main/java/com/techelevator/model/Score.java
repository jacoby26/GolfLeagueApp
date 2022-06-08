package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Score {

    @JsonProperty("round")
    private long roundID;
    private long user_id;
    private long score;
    @JsonProperty("user")
    private String username;

    public Score() {
    }

    public Score(long roundID, long user_id, long score) {
        this.roundID = roundID;
        this.user_id = user_id;
        this.score = score;
    }

    public long getRoundID() {
        return roundID;
    }

    public void setRoundID(long roundID) {
        this.roundID = roundID;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Score{" +
                "roundID=" + roundID +
                ", user_id=" + user_id +
                ", score=" + score +
                ", username='" + username + '\'' +
                '}';
    }
}
