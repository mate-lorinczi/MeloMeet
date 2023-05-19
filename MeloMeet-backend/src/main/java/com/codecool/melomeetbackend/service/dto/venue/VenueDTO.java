package com.codecool.melomeetbackend.service.dto.venue;

public record VenueDTO(String name, WholeAddress wholeAddress, boolean isOpenAir, String venueId) {
}
