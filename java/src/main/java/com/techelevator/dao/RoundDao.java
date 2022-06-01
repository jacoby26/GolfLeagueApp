package com.techelevator.dao;

import com.techelevator.model.GolfCourse;
import com.techelevator.model.Round;


import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

public interface RoundDao {

    List<Round> getAllUserRounds(Principal principal);
    long createRound(int score, LocalDate roundDate, Principal principal, GolfCourse golfCourse);
}
