package com.techelevator.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Map;

@Service
public class JdbcLeaderboardDao implements LeaderboardDao{
    private JdbcTemplate jdbctemplate;

    public JdbcLeaderboardDao(DataSource ds){jdbctemplate = new JdbcTemplate(ds);}

    public Map<Integer, Integer> getRankings(int LeagueID){
        String sql = "Select user_id, score" +
                "from leaderboards" +
                "where league_id= ?" +
                ";";
        jdbctemplate.queryForObject(sql, Map.class, LeagueID);
    }
}
