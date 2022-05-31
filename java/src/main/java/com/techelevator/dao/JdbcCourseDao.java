package com.techelevator.dao;

import com.techelevator.model.GolfCourse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcCourseDao implements CourseDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcCourseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<GolfCourse> getAllCourses() {
        List<GolfCourse> allCourses = new ArrayList<>();
        String sql = "select * from courses";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            GolfCourse golfCourse = mapRowToGolfCourse(results);
            allCourses.add(golfCourse);
        }

        return allCourses;


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
