package com.techelevator.controller;

import com.techelevator.dao.CourseDao;
import com.techelevator.dao.RoundDao;
import com.techelevator.model.GolfCourse;
import com.techelevator.model.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthorized")
public class AppController {

    @Autowired
    CourseDao courseDao;

    @Autowired
    RoundDao roundDao;

    @RequestMapping(path="/courses", method = RequestMethod.GET)
    public List<GolfCourse> listAllCourses() {
        return courseDao.getAllCourses();
    }

    @RequestMapping(path="/rounds", method = RequestMethod.GET)
    public List<Round> listAllUserRounds(Principal principal) {
        return roundDao.getAllUserRounds(principal);
    }







}
