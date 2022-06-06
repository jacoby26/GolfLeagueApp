package com.techelevator.dao;

import com.techelevator.model.*;
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
        long userID = userDao.findIdByUsername(principal.getName());
        String sql = "INSERT INTO leagues (league_name, course_id, league_organizer) "
                + "VALUES (?,?,?) RETURNING league_id";
        long leagueID = jdbcTemplate.queryForObject(sql, long.class, name, course.getId(), userID);

        String sql2 = "INSERT INTO users_leagues (user_id, league_id) "
                + "VALUES (?,?) RETURNING league_id";

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
        currentLeague.setLeaderboardTable(getRankings(set.getLong("league_id")));
        return currentLeague;
    }
    public List<LeaderboardRow> getRankings(long LeagueID){
        List<LeaderboardRow> output = new ArrayList<>();
        String sql = "select avg(scores.score)as user_league_score, username " +
                "from scores " +
                "join users on users.user_id = scores.user_id " +
                "join rounds on rounds.round_id = scores.round_id " +
                "join leagues on leagues.league_id = rounds.league_id " +
                "where leagues.league_id = ? " +
                "group by username " +
                "order by user_league_score ;";
        SqlRowSet Rankings = jdbcTemplate.queryForRowSet(sql, LeagueID);
        long i =1;
        while(Rankings.next()){
            LeaderboardRow currentRow = new LeaderboardRow();
            currentRow.setName(Rankings.getString("username"));
            currentRow.setScore(Rankings.getString("user_league_score"));
            currentRow.setRank(i);
            i++;
            output.add(currentRow);
        }
        return output;
    }
}