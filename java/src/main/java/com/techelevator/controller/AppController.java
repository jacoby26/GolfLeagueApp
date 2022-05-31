package com.techelevator.controller;

import com.techelevator.dao.CourseDao;
import com.techelevator.dao.RoundDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.GolfCourse;
import com.techelevator.model.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDate;
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

    @RequestMapping(path="/rounds/addround", method=RequestMethod.POST)
    public long addUserRound(@RequestParam int score, LocalDate roundDate, Principal principal, GolfCourse golfCourse) {

        return roundDao.createRound(score, roundDate, principal, golfCourse);
    }







}
