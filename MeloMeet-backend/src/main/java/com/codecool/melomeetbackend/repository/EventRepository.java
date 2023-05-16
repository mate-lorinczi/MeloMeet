package com.codecool.melomeetbackend.repository;

import com.codecool.melomeetbackend.model.eventModel.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
}
