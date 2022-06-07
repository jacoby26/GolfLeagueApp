package com.techelevator.dao;

import com.techelevator.model.*;

import com.techelevator.model.League;

import java.security.Principal;
import java.util.List;

public interface LeagueDao {

    long createLeague(String name, GolfCourse course, Principal principal);

    long joinLeague(Long userID, Long leagueId);

    List<League> getAllLeagues(Principal principal);

    List<LeaderboardRow> getRankings(long LeagueID);

    public List<String> getmembers(long LeagueID);

    public List<String> getNonmembers(long LeagueID);

    List<League> getManagedLeagues(Principal principal);

}
