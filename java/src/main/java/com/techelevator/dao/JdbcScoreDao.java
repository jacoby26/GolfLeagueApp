package com.techelevator.dao;

import com.techelevator.model.GolfCourse;
import com.techelevator.model.League;
import com.techelevator.model.Score;
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
public class JdbcScoreDao implements ScoreDao{

    @Autowired
    JdbcScoreDao jdbcScoreDao;
    @Autowired
    JdbcUserDao jdbcUserDao;

    private JdbcTemplate jdbcTemplate;

    public JdbcScoreDao(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    @Override
    public long enterScore(Score score) {
        long userId = jdbcUserDao.findIdByUsername(score.getUsername());
        String sql = "INSERT INTO scores (score, user_id, round_id) "
                + "VALUES (?,?,?) RETURNING score_id";
        return jdbcTemplate.queryForObject(sql, long.class, score.getScore(), userId, score.getRoundID());
    }
}


