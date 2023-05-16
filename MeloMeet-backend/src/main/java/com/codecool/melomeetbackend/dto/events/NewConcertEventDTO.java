package com.codecool.melomeetbackend.dto.events;

import com.codecool.melomeetbackend.dto.user.UserDTO;

import java.time.LocalDateTime;
import java.util.Set;

public record NewConcertEventDTO(LocalDateTime startDateAndTime,
                                 LocalDateTime endDateAndTime,
                                 Set<String> performers, UserDTO createdBy, Set<String> styles) {
}
