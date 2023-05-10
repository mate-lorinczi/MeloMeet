package com.codecool.melomeetbackend.dto;

import com.codecool.melomeetbackend.model.Performer;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public abstract class EventDTO {

    private LocalDateTime startDateAndTime;
    private LocalDateTime endDateAndTime;
    private Set<String> performers;
}
