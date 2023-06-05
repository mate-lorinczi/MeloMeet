package com.codecool.melomeetbackend.service.dto.events;

import java.time.LocalDateTime;
import java.util.Set;
//TODO: Add venue to the dto!
public record NewConcertEventDTO(LocalDateTime startDateAndTime,
                                 LocalDateTime endDateAndTime,
                                 Set<String> performers, String createdBy, Set<String> styles) {
}
