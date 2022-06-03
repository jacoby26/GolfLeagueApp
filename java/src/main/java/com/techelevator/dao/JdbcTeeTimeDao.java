package com.techelevator.dao;

import com.techelevator.model.League;
import com.techelevator.model.TeeTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcTeeTimeDao implements TeeTimeDao {

    @Autowired
    UserDao userDao;

    private JdbcTemplate jdbcTemplate;

    public JdbcTeeTimeDao(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    @Override
    public List<TeeTime> getAllTeeTimes() {
        List<TeeTime> allTeeTimes = new ArrayList<>();
        String sql = "SELECT tee_time_id, date, time, courses.course_name, users.username FROM tee_times "
                + "JOIN courses ON tee_times.course_id = courses.course_id "
                + "JOIN users ON tee_times.user.id = users.user_id";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            TeeTime teeTimes = mapRowToTeeTime(results);
            allTeeTimes.add(teeTimes);
        }
        return allTeeTimes;
    }

    @Override
    public List<TeeTime> getAllTeeTimesByUserID(Principal principal) {
        List<TeeTime> userTeeTimes = new ArrayList<>();
        String sql = "SELECT tee_time_id, date, time, courses.course_name FROM tee_times "
                + "JOIN courses ON tee_times.course_id = courses.course_id "
                + "WHERE tee_times.user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userDao.findIdByUsername(principal.getName()));
        while (results.next()) {
            TeeTime userTeeTime = mapRowToTeeTime(results);
            userTeeTimes.add(userTeeTime);
        }
        return userTeeTimes;
    }

    @Override
    public long createTeeTime() {
        return 0;
    }

    private TeeTime mapRowToTeeTime(SqlRowSet set){
        TeeTime teeTime = new TeeTime();
        teeTime.setId(set.getLong("tee_time_id"));
        teeTime.setDate(set.getDate("date").toLocalDate());
        teeTime.setTime(set.getTime("time").toLocalTime());
        teeTime.setCourseID(set.getLong("course_id"));
        teeTime.setUserID(set.getLong("user_id"));
        return teeTime;
    }
}
