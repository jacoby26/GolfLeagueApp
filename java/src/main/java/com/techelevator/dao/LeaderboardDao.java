package com.techelevator.dao;

import com.techelevator.model.GolfCourse;

import java.security.Principal;
import java.util.Map;

public interface LeaderboardDao {

    public Map<Integer, Integer> getRankings(long LeagueID);

}
