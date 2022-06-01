package com.techelevator.dao;

import com.techelevator.model.GolfCourse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;


import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcGolfCourseDao implements GolfCourseDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcGolfCourseDao(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }


    @Override
    public List<GolfCourse> getAllCourses() {
        List<GolfCourse> allCourses = new ArrayList<>();
        String sql = "SELECT * FROM courses ORDER BY course_name";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            GolfCourse golfCourse = mapRowToGolfCourse(results);
            allCourses.add(golfCourse);
        }

        return allCourses;

    }

    @Override
    public long addCourse(String name, String address, String city, String state, int zip, double longitude, double latitude) {
        String sql = "INSERT INTO courses (course_name, address, city, course_state, zip_code, latitude, longitude) "
                + "VALUES (?,?,?,?,?,?,?) RETURNING course_id";
        return jdbcTemplate.queryForObject(sql, long.class, name, address, city, state, zip, latitude, longitude);

    }


    private GolfCourse mapRowToGolfCourse(SqlRowSet rs) {
        GolfCourse golfCourse = new GolfCourse();

        golfCourse.setId(rs.getLong("course_id"));
        golfCourse.setName(rs.getString("course_name"));
        golfCourse.setAddress(rs.getString("address"));
        golfCourse.setCity(rs.getString("city"));
        golfCourse.setState(rs.getString("course_state"));
        golfCourse.setZip(rs.getInt("zip_code"));
        golfCourse.setLatitude(rs.getDouble("latitude"));
        golfCourse.setLongitude(rs.getDouble("longitude"));

        return golfCourse;
    }

}
