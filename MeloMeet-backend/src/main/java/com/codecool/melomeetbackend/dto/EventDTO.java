package com.codecool.melomeetbackend.dto;

import com.codecool.melomeetbackend.model.Performer;

import java.time.LocalDateTime;
import java.util.Set;

public abstract class EventDTO {

    private LocalDateTime startDateAndTime;
    private LocalDateTime endDateAndTime;
    private Set<String> performers;
}
