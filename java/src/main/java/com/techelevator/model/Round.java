package com.techelevator.model;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Round {

    private long id;
    private int score;
    private LocalDate date;
    private LocalTime teeTime;
    private long courseId;
    private long userId;

    public Round() { }

    public Round(long id, int score, LocalDate date, LocalTime teeTime, long courseId, long userId) {
        this.id = id;
        this.score = score;
        this.date = date;
        this.teeTime = teeTime;
        this.courseId = courseId;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public LocalTime getTeeTime() {
        return teeTime;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTeeTime(LocalTime teeTime) {
        this.teeTime = teeTime;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
