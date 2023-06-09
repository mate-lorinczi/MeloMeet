package com.codecool.melomeetbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@Entity
@Table(name = "users")
@AllArgsConstructor
public class User {

    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID userID;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String email;
    @Column
    @CreationTimestamp
    private Timestamp dateOfRegistration;
    @Column
    private boolean isAdmin;
    @JoinColumn
    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<User> friends;
    @Column
    private boolean isBanned;
}
