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
import java.util.stream.Collectors;

@Entity
@Builder
@Data
//TODO: @Table és @Column nem kell ott, ahol nem adunk meg extra kritériumot!
@Table
@NoArgsConstructor
@AllArgsConstructor
public class ConcertEvent{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID eventId;
    @Column()
    private LocalDateTime startDateAndTime;
    @Column()
    private LocalDateTime endDateAndTime;
    @Column(nullable = false)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Set<Performer> performers;
    @JoinColumn(nullable = false)
    @ManyToOne
    private Venue venue;
    @JoinColumn(nullable = false)
    @ManyToOne
    private User createdBy;
    @JoinColumn
    @ManyToMany
    private Set<Style> styles;

    public String getName() {
        return
                "Concert of [" + performers
                        .stream()
                        .map(Performer::getName)
                        .collect(Collectors.joining(" | ")) + "] at " + venue.getName();
    }
}
