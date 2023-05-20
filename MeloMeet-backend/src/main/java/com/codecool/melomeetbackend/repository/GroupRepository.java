package com.codecool.melomeetbackend.repository;

import com.codecool.melomeetbackend.model.Group;
import com.codecool.melomeetbackend.model.User;
import com.codecool.melomeetbackend.model.eventModel.ConcertEvent;
import jdk.jfr.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;
@Repository
public interface GroupRepository extends JpaRepository<Group, UUID> {

    Set<Group> findAllByOpenToNonInvitedIsTrue();
    Set<Group> findAllByOpenToNonInvitedIsTrueAndEvent(ConcertEvent concertEvent);
    Set<Group> findAllByInvitedContaining(User user);
}
