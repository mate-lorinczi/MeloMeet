package com.codecool.melomeetbackend.model.eventModel;

import com.codecool.melomeetbackend.model.Performer;
import com.codecool.melomeetbackend.model.Style;
import com.codecool.melomeetbackend.model.User;
import com.codecool.melomeetbackend.model.Venue;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class ConcertEvent{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID eventId;
    @Column
    private LocalDateTime startDateAndTime;
    @Column
    private LocalDateTime endDateAndTime;
    @Column
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn
    private Set<Performer> performers;
    @JoinColumn
    @ManyToOne
    private Venue venue;
    @JoinColumn
    @ManyToOne
    private User createdBy;
    @JoinColumn
    @ManyToMany
    private Set<Style> styles;
}
