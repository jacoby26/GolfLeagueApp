package com.techelevator.model;

import java.security.Principal;
import java.time.LocalDate;

public class Round {

    private Long id;
    private int score;
    private LocalDate date;
    private String course;
    private String player;

    public Round() { }

    public Round(Long id, int score, LocalDate date, String course, String player) {
        this.id = id;
        this.score = score;
        this.date = date;
        this.course = course;
        this.player = player;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return date + " " + course + " " + score;
    }
}
