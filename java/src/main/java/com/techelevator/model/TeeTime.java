package com.techelevator.model;

import com.techelevator.dao.GolfCourseDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;

public class TeeTime {

    @Autowired
    GolfCourseDao golfCourseDao;

    private long id;
    private LocalDate date;
    private LocalTime time;
    private long courseID;
    private long userID;

    public TeeTime() { }

    public TeeTime(long id, LocalDate date, LocalTime time, long courseID, long userID) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.courseID = courseID;
        this.userID = userID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public long getCourseID() {
        return courseID;
    }

    public void setCourseID(long courseID) {
        this.courseID = courseID;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Date: " + date + "\n" + "Start Time: " + time + "\n" + "Course: " + golfCourseDao.getCourseNameByID(courseID);

    }
}
