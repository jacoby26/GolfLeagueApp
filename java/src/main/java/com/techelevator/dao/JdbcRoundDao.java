package com.techelevator.dao;

import com.techelevator.model.GolfCourse;
import com.techelevator.model.Round;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class JdbcRoundDao implements RoundDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcRoundDao(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    @Override
    public List<Round> getAllUserRounds(Principal principal) {
        List<Round> userRounds = new ArrayList<>();
        String sql = "SELECT * FROM rounds "
                + "JOIN users ON rounds.player = users.username "
                + "JOIN courses ON rounds.course = courses.course.id "
                + "WHERE player = ? ORDER BY date";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, principal.getName());
        while(results.next()) {
            Round round = mapRowToRound(results);
            userRounds.add(round);
        }
        return userRounds;
    }

    private Round mapRowToRound(SqlRowSet rs) {

        Round round = new Round();

        round.setId(rs.getLong("course_id"));
        round.setScore(rs.getInt("score"));
        round.setDate(rs.getDate("date").toLocalDate());
        round.setCourse(rs.getString("course_name"));
        round.setPlayer(rs.getString("username"));


        return round;
    }

}
