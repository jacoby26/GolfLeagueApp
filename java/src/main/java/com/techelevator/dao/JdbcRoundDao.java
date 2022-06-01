package com.techelevator.dao;

import com.techelevator.model.GolfCourse;
import com.techelevator.model.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
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
                + "JOIN users ON rounds.user_id = users.user_id"
                + "JOIN courses ON rounds.course_id = courses.course.id "
                + "WHERE username = ? ORDER BY date";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, principal.getName());
        while(results.next()) {
            Round round = mapRowToRound(results);
            userRounds.add(round);
        }
        return userRounds;
    }

    @Override
    public long createRound(int score, LocalDate roundDate, Principal principal, GolfCourse golfCourse) {
        String sql = "INSERT INTO rounds (score, date, course_id, user_id "
                + "VALUES (?,?,?,?) RETURNING round_id";
        long userID = jdbcUserDao.findIdByUsername(principal.getName());
        long courseID = golfCourse.getId();
        return jdbcTemplate.queryForObject(sql, long.class, score, roundDate, courseID, userID);

    }


    private Round mapRowToRound(SqlRowSet rs) {

        Round round = new Round();

        round.setId(rs.getLong("course_id"));
        round.setScore(rs.getInt("score"));
        round.setDate(rs.getDate("date").toLocalDate());
        round.setCourseId(rs.getLong("course_id"));
        round.setUserId(rs.getLong("user_id"));


        return round;
    }

}
