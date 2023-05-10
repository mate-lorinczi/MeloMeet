package com.codecool.melomeetbackend.model.eventModel;

import com.codecool.melomeetbackend.model.Performer;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@DiscriminatorColumn(name = "event_type", discriminatorType = DiscriminatorType.INTEGER)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID eventId;
    @Column
    private LocalDateTime startDateAndTime;
    @Column
    private LocalDateTime endDateAndTime;
    @Column
    @ManyToMany
    @JoinColumn
    private Set<Performer> performers;
}
