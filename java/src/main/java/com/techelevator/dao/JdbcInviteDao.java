package com.techelevator.dao;

import com.techelevator.model.Invite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcInviteDao implements InviteDao{
    private JdbcTemplate jdbcTemplate;
    @Autowired
    JdbcUserDao userDao;
    public JdbcInviteDao(DataSource ds){
        this.jdbcTemplate = new JdbcTemplate(ds);
            }

    @Override
    public void makeInvite(Invite invitation) {
        invitation.setUserId(userDao.findIdByUsername(invitation.getLeagueAdmin()));
        String sql = "Insert into invites " +
            "(league_id, user_id) " +
            "Values (?,?) ;";
        jdbcTemplate.update(sql,invitation.getLeagueId(),invitation.getUserId());
    }

    @Override
    public List<Invite> getInvites(Principal principal) {
        List<Invite> output = new ArrayList<>();
        String sql = "Select invite_id, league_id, user_id " +
                "from invites " +
//                "join leagues on invites.league_id = leagues.league_id " +
//                "join users on invites.user_id = users.user_id " +
//                "join courses on leagues.course_id = users.course_id " +
                "where user_id = ? AND is_Accepted is null ;";
        long userId = userDao.findIdByUsername(principal.getName());
        SqlRowSet query = jdbcTemplate.queryForRowSet(sql, userId);
        while (query.next()){
            output.add(mapInvite(query));
        }

        return output;
    }

    @Override
    public void actOnInvite(Invite invitation) {
        String sql = "update invites " +
                "set is_Accepted = ? " +
                "where invite_id = ? ;";
        jdbcTemplate.update(sql,invitation.isAccepted(),invitation.getInviteId());
    }
    public Invite mapInvite(SqlRowSet set){
        Invite currentInvitation = new Invite();
        currentInvitation.setUserId(set.getLong("user_Id"));
        currentInvitation.setInviteId(set.getLong("invite_id"));
        currentInvitation.setLeagueId(set.getLong("league_id"));
        currentInvitation.setLeagueName(
                jdbcTemplate.queryForObject("select league_name " +
                        "from leagues " +
                        "where league_id = ?;", String.class, currentInvitation.getLeagueId()));
        currentInvitation.setLeagueAdmin(
                jdbcTemplate.queryForObject("select username from users " +
                        "join leagues on users.user_id = leagues.league_organizer " +
                        "where league_id = ?;", String.class, currentInvitation.getLeagueId()));
        Long courseId = jdbcTemplate.queryForObject("select course_id " +
                "from Leagues " +
                "where league_id = ?;", Long.class, currentInvitation.getLeagueId());
        currentInvitation.setCourseName(
                jdbcTemplate.queryForObject("select course_name " +
                        "from courses " +
                        "where course_id = ?", String.class, courseId));
        String courseAddress = "";
        courseAddress += jdbcTemplate.queryForObject("select address " +
                "from courses " +
                "where course_id = ?;", String.class, courseId);
        courseAddress += " ";
        courseAddress += jdbcTemplate.queryForObject("select city " +
                "from courses " +
                "where course_id = ?;", String.class, courseId);
        courseAddress += ", ";
        courseAddress += jdbcTemplate.queryForObject("select course_state " +
                "from courses " +
                "where course_id = ?;", String.class, courseId);
        courseAddress += " ";
        courseAddress += jdbcTemplate.queryForObject("select zip_code " +
                "from courses " +
                "where course_id = ?;", String.class, courseId);
        currentInvitation.setCourseAddress(courseAddress);
        return currentInvitation;
    }
}
