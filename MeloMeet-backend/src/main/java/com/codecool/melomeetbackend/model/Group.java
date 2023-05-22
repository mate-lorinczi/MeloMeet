package com.codecool.melomeetbackend.model;

import com.codecool.melomeetbackend.model.eventModel.ConcertEvent;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<User> members;
    @Column
    private boolean openToNonInvited;
    @JoinColumn
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<User> invited;
    @JoinColumn
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ConcertEvent event;
}
