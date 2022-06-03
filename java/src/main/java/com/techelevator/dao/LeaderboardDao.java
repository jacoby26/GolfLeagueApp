package com.techelevator.dao;

import com.techelevator.model.LeaderboardRow;

import java.util.List;

public interface LeaderboardDao {

    public List<LeaderboardRow> getRankings(long LeagueID);

}
