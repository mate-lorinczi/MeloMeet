package com.codecool.melomeetbackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID groupId;
    @JoinColumn
    @ManyToOne
    private User creator;
    @JoinColumn
    @ManyToMany
    private Set<User> members;
    @Column
    private boolean isOpenToNonInvited;
    @JoinColumn
    @ManyToMany
    private Set<User> invited;
}
