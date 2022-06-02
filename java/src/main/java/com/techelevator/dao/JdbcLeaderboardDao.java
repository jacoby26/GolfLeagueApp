package com.techelevator.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Service
public class JdbcLeaderboardDao implements LeaderboardDao{
    private JdbcTemplate jdbctemplate;

    public JdbcLeaderboardDao(DataSource ds){jdbctemplate = new JdbcTemplate(ds);}

    public List<> getRankings(long LeagueID){
        String sql = "Select user_id, score " +
                "from rounds " +
                "where league_id= ? " +
                ";";
        return jdbctemplate.queryForObject(sql, HashMap.class, LeagueID);
    }
}
