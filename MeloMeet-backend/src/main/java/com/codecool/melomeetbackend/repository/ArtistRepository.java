package com.codecool.melomeetbackend.repository;

import com.codecool.melomeetbackend.model.Performer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ArtistRepository extends JpaRepository<Performer, UUID> {
}
