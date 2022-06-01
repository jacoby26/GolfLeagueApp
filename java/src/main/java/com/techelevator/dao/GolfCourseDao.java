package com.techelevator.dao;

import com.techelevator.model.GolfCourse;

import java.util.List;

public interface GolfCourseDao {

    List<GolfCourse> getAllCourses();

    long addCourse(String name, String address, String city, String state, int zip, double longitude, double latitude);
}
