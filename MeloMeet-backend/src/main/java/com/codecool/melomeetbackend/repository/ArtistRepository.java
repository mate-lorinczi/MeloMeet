package com.codecool.melomeetbackend.repository;

import com.codecool.melomeetbackend.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArtistRepository extends JpaRepository<Artist, UUID> {
}
