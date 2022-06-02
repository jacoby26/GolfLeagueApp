package com.techelevator.dao;

import com.techelevator.model.GolfCourse;

import java.util.List;

public interface GolfCourseDao {

    List<GolfCourse> getAllCourses();

    boolean addCourse(String course_name, String address, String city, String course_state, Integer zip_code, Double latitude, Double longitude);

    String getCourseNameByID(long courseID);
}
