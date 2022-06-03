package com.techelevator.dao;

import com.techelevator.model.TeeTime;

import java.security.Principal;
import java.util.List;

public interface TeeTimeDao {

    List<TeeTime> getAllTeeTimes();
    List<TeeTime> getAllTeeTimesByUserID(Principal principal);
    long createTeeTime();
}
