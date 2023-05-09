package com.codecool.melomeetbackend.model.eventModel;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID eventId;
    @Column
    private LocalDateTime startDateAndTime;
    @Column
    private LocalDateTime endDateAndTime;
}
