package com.codecool.melomeetbackend.repository;

import com.codecool.melomeetbackend.model.eventModel.ConcertEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ConcertEventRepository extends JpaRepository<ConcertEvent, UUID> {
}