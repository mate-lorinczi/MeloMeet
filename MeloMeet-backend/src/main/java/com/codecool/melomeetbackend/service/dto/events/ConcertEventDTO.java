package com.codecool.melomeetbackend.service.dto.events;

import com.codecool.melomeetbackend.service.dto.user.UserDTO;
import com.codecool.melomeetbackend.model.Performer;
import com.codecool.melomeetbackend.model.Style;
import com.codecool.melomeetbackend.service.dto.venue.SimpleVenueDTO;
import com.codecool.melomeetbackend.service.dto.venue.VenueDTO;

import java.time.LocalDateTime;
import java.util.Set;

public record ConcertEventDTO(String eventId, LocalDateTime startDateAndTime,
                              LocalDateTime endDateAndTime,
                              Set<Performer> performers, UserDTO createdBy, Set<Style> styles,
                              SimpleVenueDTO simpleVenueDTO, String eventName) {
}
