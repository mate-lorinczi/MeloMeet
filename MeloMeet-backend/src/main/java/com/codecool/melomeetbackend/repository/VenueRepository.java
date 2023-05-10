package com.codecool.melomeetbackend.repository;

import com.codecool.melomeetbackend.model.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VenueRepository extends JpaRepository<Venue, UUID> {
}
