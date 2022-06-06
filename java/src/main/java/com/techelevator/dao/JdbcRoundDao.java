package com.techelevator.dao;

import com.techelevator.model.GolfCourse;
import com.techelevator.model.League;
import com.techelevator.model.Round;
import com.techelevator.model.TeeTime;
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
                + "JOIN tee_times ON rounds.tee_time_id = tee_times.tee_time_id "
                + "JOIN users ON tee_times.user_id = users.users_id "
                + "JOIN courses ON tee_times.course_id = courses.course.id "
                + "WHERE username = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, principal.getName());
        while(results.next()) {
            Round round = mapRowToRound(results);
            userRounds.add(round);
        }
        return userRounds;
    }

    @Override
    public long createRound(int score, TeeTime teeTime) {
        String sql = "INSERT INTO rounds (tee_time_id, score) "
                + "VALUES (?,?,?,?,?) RETURNING round_id";
        return jdbcTemplate.queryForObject(sql, long.class, score, teeTime.getId());

    }
    @Override
    public long newRound(LocalTime teeTime, LocalDate date, League league)
    {
        String sql = "INSERT INTO rounds (tee_time, date, league_id) "
                + "VALUES (?,?,?,?,?) RETURNING round_id";
        return jdbcTemplate.queryForObject(sql, long.class, teeTime, date, league.getLeagueID());
    }



    private Round mapRowToRound(SqlRowSet rs) {

        Round round = new Round();

        round.setId(rs.getLong("course_id"));
        round.setTeeTimeID(rs.getLong("tee_time_id"));
        round.setScore(rs.getInt("score"));

        return round;
    }

}
