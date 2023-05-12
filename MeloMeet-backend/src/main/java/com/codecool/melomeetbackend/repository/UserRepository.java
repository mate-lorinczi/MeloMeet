package com.codecool.melomeetbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<com.codecool.melomeetbackend.model.User, UUID> {
}
