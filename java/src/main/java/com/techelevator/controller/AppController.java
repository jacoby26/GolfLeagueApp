package com.techelevator.controller;

import com.techelevator.dao.CourseDao;
import com.techelevator.model.GolfCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@PreAuthorize("isAuthorized")
public class AppController {

    @Autowired
    CourseDao courseDao;

    @RequestMapping(path="/courses", method = RequestMethod.GET)
    public List<GolfCourse> listAllCourses() {
        return courseDao.getAllCourses();
    }







}
