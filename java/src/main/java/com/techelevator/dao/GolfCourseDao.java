package com.techelevator.dao;

import com.techelevator.model.GolfCourse;

import java.util.List;

public interface GolfCourseDao {

    List<GolfCourse> getAllCourses();

    long addCourse(GolfCourse golfCourse);
}
