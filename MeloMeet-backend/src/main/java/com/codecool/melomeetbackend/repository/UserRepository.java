package com.codecool.melomeetbackend.repository;

import com.codecool.melomeetbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<com.codecool.melomeetbackend.model.User, UUID> {

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<User> findByUsername(String username);
    Set<User> findAllByUsernameContaining(String searchString);
    Optional<User> findByUsernameAndPassword(String username, String password);
}
