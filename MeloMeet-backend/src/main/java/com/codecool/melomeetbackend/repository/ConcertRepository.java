package com.codecool.melomeetbackend.repository;

import com.codecool.melomeetbackend.model.eventModel.ConcertEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ConcertRepository extends JpaRepository<ConcertEvent, UUID> {
}
