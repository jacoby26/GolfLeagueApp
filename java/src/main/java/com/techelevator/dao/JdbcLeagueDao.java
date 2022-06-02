package com.techelevator.dao;

import com.techelevator.model.GolfCourse;
import com.techelevator.model.League;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

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

    @Override
    public long joinLeague(User user, League league) {
        String sql = "INSERT INTO users_leagues (user_id, league_id) "
                + "VALUES (?,?) RETURNING league_id";
        long userID = userDao.findIdByUsername(user.getUsername());
        return jdbcTemplate.queryForObject(sql, long.class, userID, league.getLeagueID());
    }


}