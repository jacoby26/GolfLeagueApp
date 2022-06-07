package com.techelevator.dao;

import com.techelevator.model.Invite;

import java.security.Principal;
import java.util.List;

public interface InviteDao {
    public void makeInvite(Invite invitation);
    public List<Invite> getInvites(Principal principal);
    public void actOnInvite(Invite invitation);
}
