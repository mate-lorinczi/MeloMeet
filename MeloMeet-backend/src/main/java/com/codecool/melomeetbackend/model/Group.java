package com.codecool.melomeetbackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID groupId;
    @JoinColumn
    @Column
    @ManyToOne
    private User creator;
    @Column
    @JoinColumn
    @ManyToMany
    private Set<User> members;
    @Column
    private boolean isOpenToNonInvited;
    @Column
    @JoinColumn
    @ManyToMany
    private Set<User> invited;
}
