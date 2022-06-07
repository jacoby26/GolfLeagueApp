package com.techelevator.controller;


import com.techelevator.dao.GolfCourseDao;
import com.techelevator.dao.LeagueDao;
import com.techelevator.dao.RoundDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.*;

import com.techelevator.dao.*;
import com.techelevator.model.GolfCourse;
import com.techelevator.model.League;
import com.techelevator.model.Round;
import com.techelevator.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.techelevator.model.League;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

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
    @Autowired
    UserDao UserDao;

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
                    , golfCourse.getZip()
                    , golfCourse.getLatitude()
                    , golfCourse.getLongitude());
    }

    @RequestMapping(path="/leagues/addleague", method=RequestMethod.POST)
    public long addLeague(@RequestBody League league, Principal principal) {

        return leagueDao.createLeague(league.getName(), league.getGolfCourse(), principal);
    }

    @RequestMapping(path="/leagues/", method=RequestMethod.GET)
    public List<League> getAllLeagues(Principal principal){
        return leagueDao.getAllLeagues(principal);
    }

    @RequestMapping (path="/leagues/join", method=RequestMethod.POST)
    public long joinLeague(@RequestBody User user, League league) {
        return leagueDao.joinLeague(user,league);
    }

    @RequestMapping(path="/rounds", method=RequestMethod.GET)
    public List<Round> listAllUserRounds(Principal principal) {
        return roundDao.getAllUserRounds(principal);
    }

//    @RequestMapping(path="/rounds/addround", method=RequestMethod.POST)
//    public long addUserRound(@RequestParam Round round) {
//
//        return roundDao.createRound(round);
//    }
    @RequestMapping(path="/leagues/addround", method=RequestMethod.POST)
    public long createNewRound(@RequestParam LocalTime teeTime, LocalDate date, League league) {

        return roundDao.newRound(teeTime, date, league);
    }
    @RequestMapping(path="/leagues/addround1", method=RequestMethod.POST)
    public long createRound(@RequestParam Round round) {

        return roundDao.createRound(score, teeTime);

    }
    @RequestMapping(path="/users", method=RequestMethod.GET)
    public List<User> findAll(){
        return UserDao.findAll();
    }
}
