package com.codecool.melomeetbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface User extends JpaRepository<User, UUID> {
}
