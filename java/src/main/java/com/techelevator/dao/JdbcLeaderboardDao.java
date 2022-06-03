package com.techelevator.dao;

import com.techelevator.model.LeaderboardRow;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcLeaderboardDao {
    private JdbcTemplate jdbctemplate;

    public JdbcLeaderboardDao(DataSource ds){jdbctemplate = new JdbcTemplate(ds);}


}
