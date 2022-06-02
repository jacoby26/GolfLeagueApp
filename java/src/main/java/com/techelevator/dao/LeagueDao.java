package com.techelevator.dao;

import com.techelevator.model.GolfCourse;

import com.techelevator.model.League;

import java.security.Principal;

public interface LeagueDao {

    long createLeague(String name, GolfCourse course, Principal principal);

    League[] getAllLeagues(Principal principal);
}
