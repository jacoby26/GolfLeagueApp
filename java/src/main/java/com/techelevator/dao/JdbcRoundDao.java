package com.techelevator.dao;

import com.techelevator.model.GolfCourse;
import com.techelevator.model.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcRoundDao implements RoundDao{

    @Autowired
    JdbcUserDao jdbcUserDao;

    private JdbcTemplate jdbcTemplate;

    public JdbcRoundDao(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    @Override
    public List<Round> getAllUserRounds(Principal principal) {
        List<Round> userRounds = new ArrayList<>();
        String sql = "SELECT * FROM rounds "
    //            + "JOIN scores ON rounds.round_id = scores.round_id "
                + "JOIN leagues ON leagues.league_id = rounds.league_id "
    //            + "JOIN users_leagues ON rounds.league_id = leagues.league_id "
                + "JOIN courses ON leagues.course_id = courses.course_id "
                + "WHERE rounds.league_id IN (SELECT league_id FROM users_leagues where user_id = ? ) ;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, jdbcUserDao.findIdByUsername(principal.getName()));
        while(results.next()) {
            Round round = mapRowToRound(results);
            userRounds.add(round);
        }
        return userRounds;
    }

    @Override
    public long createRound(Round round) {
        String sql = "INSERT INTO rounds (tee_time, round_date, league_id) "
                + "VALUES (?,?,?,?,?) RETURNING round_id";
        return jdbcTemplate.queryForObject(sql, long.class, round.getTeeTime(), round.getDate(), round.getLeagueID());

    }


    private Round mapRowToRound(SqlRowSet rs) {

        Round round = new Round();

        round.setId(rs.getLong("course_id"));
        round.setTeeTime(rs.getTime("tee_time").toLocalTime());
        round.setDate(rs.getDate("round_date").toLocalDate());
        round.setLeagueID(rs.getLong("league_id"));
        round.setLeagueName(rs.getString("league_name"));
        return round;
    }

}
