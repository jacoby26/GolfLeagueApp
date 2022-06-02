package com.techelevator.dao;

import com.techelevator.model.GolfCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import com.techelevator.model.League;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

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

    public List<League> getAllLeagues(Principal principal){
        List<League> output = new ArrayList();
        String sql = "SELECT * " +
                "from leagues " +
                "join users_leagues on users_leagues.league_id = leagues.league_id " +
                "where users_leagues.user_id = ? " +
                ";";
//        SqlRowSet query = jdbcTemplate.queryForRowSet(sql);
        SqlRowSet query = jdbcTemplate.queryForRowSet(sql, userDao.findIdByUsername(principal.getName()));
        while (query.next()){
            output.add(mapRowToLeague(query));
        }
        return output;
    }
    private League mapRowToLeague(SqlRowSet set){
        League currentLeague = new League();
        currentLeague.setCourseID(set.getLong("course_id"));
        currentLeague.setLeagueID(set.getLong("league_id"));
        currentLeague.setName(set.getString("league_name"));
        return currentLeague;
    }
}