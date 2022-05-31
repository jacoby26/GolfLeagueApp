package com.techelevator.dao;

import com.techelevator.model.Round;

import java.security.Principal;
import java.util.List;

public interface RoundDao {

    List<Round> getAllUserRounds(Principal principal);
}
