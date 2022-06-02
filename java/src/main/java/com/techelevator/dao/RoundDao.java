package com.techelevator.dao;

import com.techelevator.model.GolfCourse;
import com.techelevator.model.Round;
import com.techelevator.model.TeeTime;


import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface RoundDao {

    List<Round> getAllUserRounds(Principal principal);
    long createRound(int score, TeeTime teeTime);
}
