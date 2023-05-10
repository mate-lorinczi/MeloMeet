package com.codecool.melomeetbackend.repository;

import com.codecool.melomeetbackend.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GroupRepository extends JpaRepository<Group, UUID> {
}
