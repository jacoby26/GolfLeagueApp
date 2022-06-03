package com.techelevator.dao;

import com.techelevator.model.GolfCourse;
import com.techelevator.model.League;
import com.techelevator.model.User;

import com.techelevator.model.League;

import java.security.Principal;
import java.util.List;

public interface LeagueDao {

    long createLeague(String name, GolfCourse course, Principal principal);

    long joinLeague(User user, League league);

    List<League> getAllLeagues(Principal principal);

}
