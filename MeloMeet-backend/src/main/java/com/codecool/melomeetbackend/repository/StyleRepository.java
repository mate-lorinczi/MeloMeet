package com.codecool.melomeetbackend.repository;

import com.codecool.melomeetbackend.model.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface StyleRepository extends JpaRepository<Style, Long> {

    Optional<Style> findByName(String name);
}
