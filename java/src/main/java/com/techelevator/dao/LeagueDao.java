package com.techelevator.dao;

import com.techelevator.model.GolfCourse;

import com.techelevator.model.League;

import java.security.Principal;
import java.util.List;

public interface LeagueDao {

    long createLeague(String name, GolfCourse course, Principal principal);

    List<League> getAllLeagues(Principal principal);
}
