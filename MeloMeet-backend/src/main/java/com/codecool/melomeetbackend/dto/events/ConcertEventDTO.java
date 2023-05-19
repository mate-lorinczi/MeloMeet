package com.codecool.melomeetbackend.dto.events;

import com.codecool.melomeetbackend.dto.user.UserDTO;
import com.codecool.melomeetbackend.model.Performer;
import com.codecool.melomeetbackend.model.Style;

import java.time.LocalDateTime;
import java.util.Set;

public record ConcertEventDTO(String eventId, LocalDateTime startDateAndTime,
                              LocalDateTime endDateAndTime,
                              Set<Performer> performers, UserDTO createdBy, Set<Style> styles) {
}
