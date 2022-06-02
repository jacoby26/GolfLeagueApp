package com.techelevator.dao;

import com.techelevator.model.GolfCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.techelevator.model.League;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.security.Principal;

@Service
public class JdbcLeagueDao implements LeagueDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcLeagueDao(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    @Autowired
    GolfCourseDao golfCourseDao;
    @Autowired
    UserDao userDao;


    @Override
    public long createLeague(String name, GolfCourse course, Principal principal) {
        String sql = "INSERT INTO leagues (league_name, course_id) "
                + "VALUES (?,?) RETURNING league_id";
        long leagueID = jdbcTemplate.queryForObject(sql, long.class, name, course.getId());

        String sql2 = "INSERT INTO users_leagues (user_id, league_id) "
                + "VALUES (?,?) RETURNING league_id";
        long userID = userDao.findIdByUsername(principal.getName());
        jdbcTemplate.queryForObject(sql2, long.class, userID, leagueID);

        return leagueID;
    }

    public League[] getAllLeagues(Principal principal){
        String sql = "SELECT *" +
                "from leagues" +
                "join users_leagues on users_leagues.league_id = leagues.league_id" +
                "where user_id = ? ;";
        return jdbcTemplate.queryForObject(sql, League[].class, userDao.findIdByUsername(principal.getName()));
    }
}