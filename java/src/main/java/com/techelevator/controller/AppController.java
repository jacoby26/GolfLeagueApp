package com.techelevator.controller;

import com.techelevator.dao.GolfCourseDao;
import com.techelevator.dao.LeagueDao;
import com.techelevator.dao.RoundDao;
import com.techelevator.model.*;
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
    public boolean addGolfCourse(@RequestBody GolfCourse golfCourse) {
        return golfCourseDao.addCourse(golfCourse.getName()
                    , golfCourse.getAddress()
                    , golfCourse.getCity()
                    , golfCourse.getState()
                    ,golfCourse.getZip()
                    , golfCourse.getLatitude()
                    , golfCourse.getLongitude());
    }

    @RequestMapping(path="/leagues/addleague", method=RequestMethod.POST)
    public long addLeague(@RequestParam String name, GolfCourse course, Principal principal) {
        return leagueDao.createLeague(name,course,principal);
    }

    @RequestMapping (path="/leagues/join", method=RequestMethod.POST)
    public long joinLeague(@RequestBody User user, League league) {
        return leagueDao.joinLeague(user,league);
    }

// Round will probably be changed a lot over the next day or so.

    @RequestMapping(path="/rounds", method=RequestMethod.GET)
    public List<Round> listAllUserRounds(Principal principal) {
        return roundDao.getAllUserRounds(principal);
    }

    @RequestMapping(path="/rounds/addround", method=RequestMethod.POST)
    public long addUserRound(@RequestParam int score, TeeTime teeTime) {

        return roundDao.createRound(score, teeTime);
    }
}
