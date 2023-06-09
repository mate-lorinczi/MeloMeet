package com.codecool.melomeetbackend.repository;

import com.codecool.melomeetbackend.model.Performer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface PerformerRepository extends JpaRepository<Performer, UUID> {

    Optional<Performer> findByName(String name);
    boolean existsByName(String name);
    Set<Performer> findAllByNameContaining(String fractionOfName);
}
