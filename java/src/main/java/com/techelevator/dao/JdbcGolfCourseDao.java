package com.techelevator.dao;

import com.techelevator.model.GolfCourse;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;


import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
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
    public boolean addCourse(String course_name, String address, String city, String course_state, Integer zip_code, Double latitude, Double longitude) {
        String sql = "INSERT INTO courses (course_name, address, city, course_state, zip_code, latitude, longitude) "
                + "VALUES (?,?,?,?,?,?,?) RETURNING course_id";
       // try {
            jdbcTemplate.queryForObject(sql
                    , Long.class
                    , course_name
                    , address
                    , city
                    , course_state
                    , zip_code
                    , latitude
                    , longitude);
        //} catch (DataAccessException e) {
           // return false;
        //}
        return true;
    }
    // String name, String address, String city, String state, int zip, double longitude, double latitude

    @Override
    public String getCourseNameByID(long courseID) {
        String sql = "SELECT course_name FROM courses WHERE course_id = ?";
        return jdbcTemplate.queryForObject(sql, String.class, courseID);
    }

    @Override
    public GolfCourse getCourseByCourseId(Long courseId) {
        System.out.println(courseId);
        String sql = "SELECT * from courses WHERE course_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, courseId);
        GolfCourse golfCourse = new GolfCourse();
        if(results.next()) {
            golfCourse = mapRowToGolfCourse(results);
        }
        return golfCourse;

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
