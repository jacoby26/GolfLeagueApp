package com.techelevator.controller;

import com.techelevator.dao.GolfCourseDao;
import com.techelevator.dao.LeagueDao;
import com.techelevator.dao.RoundDao;
import com.techelevator.model.GolfCourse;
import com.techelevator.model.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@CrossOrigin
@RestController
//@PreAuthorize("isAuthorized")
public class AppController {

    @Autowired
    GolfCourseDao golfCourseDao;
    @Autowired
    RoundDao roundDao;
    @Autowired
    LeagueDao leagueDao;


    @RequestMapping(path="/courses", method=RequestMethod.GET)
    public List<GolfCourse> listAllCourses() {
        return golfCourseDao.getAllCourses();
    }

    @RequestMapping(path="/add-course", method=RequestMethod.POST)
    public boolean addGolfCourse(@RequestBody String course_name, String address, String city, String course_state, Integer zip_code, Double latitude, Double longitude) {
        return golfCourseDao.addCourse(course_name, address, city, course_state, zip_code, latitude, longitude);
    }

    @RequestMapping(path="/leagues/addleague", method=RequestMethod.POST)
    public long addLeague(@RequestParam String name, GolfCourse course, Principal principal) {
        return leagueDao.createLeague(name,course,principal);
    }

// Round will probably be changed a lot over the next day or so.

    @RequestMapping(path="/rounds", method=RequestMethod.GET)
    public List<Round> listAllUserRounds(Principal principal) {
        return roundDao.getAllUserRounds(principal);
    }

    @RequestMapping(path="/rounds/addround", method=RequestMethod.POST)
    public long addUserRound(@RequestParam int score, LocalDate roundDate, LocalTime teeTime, Principal principal, GolfCourse golfCourse) {

        return roundDao.createRound(score, roundDate, teeTime, principal, golfCourse);
    }
}
