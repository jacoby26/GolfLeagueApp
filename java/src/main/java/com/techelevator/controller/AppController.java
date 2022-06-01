package com.techelevator.controller;

import com.techelevator.dao.GolfCourseDao;
import com.techelevator.dao.RoundDao;
import com.techelevator.model.GolfCourse;
import com.techelevator.model.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
//@PreAuthorize("isAuthorized")
public class AppController {

    @Autowired
    GolfCourseDao golfCourseDao;
    @Autowired
    RoundDao roundDao;


    @RequestMapping(path="/courses", method = RequestMethod.GET)
    public List<GolfCourse> listAllCourses() {
        return golfCourseDao.getAllCourses();
    }

    @RequestMapping(path="/rounds", method = RequestMethod.GET)
    public List<Round> listAllUserRounds(Principal principal) {
        return roundDao.getAllUserRounds(principal);
    }

    @RequestMapping(path="/rounds/addround", method=RequestMethod.POST)
    public long addUserRound(@RequestParam int score, LocalDate roundDate, Principal principal, GolfCourse golfCourse) {

        return roundDao.createRound(score, roundDate, principal, golfCourse);
    }

    @RequestMapping(path="/courses/addcourse", method=RequestMethod.POST)
    public long addGolfCourse(@RequestParam String name, String address, String city, String state, int zip, double longitude, double latitude) {
        return golfCourseDao.addCourse(name, address, city, state, zip, longitude, latitude);
    }

}
