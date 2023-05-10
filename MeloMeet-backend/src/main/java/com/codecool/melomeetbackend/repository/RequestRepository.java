package com.codecool.melomeetbackend.repository;

import com.codecool.melomeetbackend.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RequestRepository extends JpaRepository<Request, UUID> {
}
