package com.techelevator.controller;

import com.techelevator.dao.GolfCourseDao;
import com.techelevator.dao.LeagueDao;
import com.techelevator.dao.RoundDao;
import com.techelevator.dao.UserDao;
import com.techelevator.dao.ScoreDao;
import com.techelevator.model.*;

import com.techelevator.dao.*;
import com.techelevator.model.GolfCourse;
import com.techelevator.model.League;
import com.techelevator.model.Round;
import com.techelevator.model.User;
import com.techelevator.model.Score;

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
    InviteDao inviteDao;
    @Autowired
    UserDao userDao;
    @Autowired
    ScoreDao scoreDao;

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


// Round will probably be changed a lot over the next day or so.
    @RequestMapping (path="/leagues/join", method=RequestMethod.POST)
    public long joinLeague(@RequestBody User user, League league) {
        return leagueDao.joinLeague(user.getId(),league.getLeagueID());
    }

    @RequestMapping(path="/rounds", method=RequestMethod.GET)
    public List<Round> listAllUserRounds(Principal principal) {
        return roundDao.getAllUserRounds(principal);
    }

    @RequestMapping(path="/rounds/addround/user", method=RequestMethod.POST)
    public long addUserRound(@RequestBody Score score) {
        System.out.println(score);
        return scoreDao.enterScore(score);
    }

//    @RequestMapping(path="/users", method=RequestMethod.GET)
//    public List<User> findAll(){
//        return UserDao.findAll();
//    }
    @RequestMapping(path="/leagues/addround", method=RequestMethod.POST)
    public long createNewRound(@RequestBody Round round) {
        return roundDao.createRound(round);
    }
    @RequestMapping(path = "/invites", method = RequestMethod.GET)
    public List<Invite> listAllUserInvites(Principal principal) { return inviteDao.getInvites(principal);}

    @RequestMapping(path = "/invites", method = RequestMethod.POST)
    public void addInvite(@RequestBody Invite invite){
        inviteDao.makeInvite(invite);
    }
    @RequestMapping(path = "/invites", method = RequestMethod.PUT)
    public void actOnInvite(@RequestBody Invite invite){
        inviteDao.actOnInvite(invite);
        if (invite.isAccepted()){
            leagueDao.joinLeague(invite.getUserId(), invite.getLeagueId());
        }
    }
    @RequestMapping(path="/leagues/{id}/members", method = RequestMethod.GET)
    public List<String> getmembers(@PathVariable long id){
        return leagueDao.getmembers(id);
    }

    @RequestMapping(path="/leagues/{id}/Nonmembers", method = RequestMethod.GET)
    public List<String> getNonmembers(@PathVariable long id){
        return leagueDao.getNonmembers(id);
    }

    @RequestMapping(path="/leagues/Managed", method=RequestMethod.GET)
    public List<League> getManagedLeagues(Principal principal){
        return leagueDao.getManagedLeagues(principal);
    }

    @RequestMapping(path = "/courses/{courseID}", method = RequestMethod.GET)
    public GolfCourse getLatLon(@PathVariable long courseID) {return golfCourseDao.getCourseByCourseId(courseID);}
}
