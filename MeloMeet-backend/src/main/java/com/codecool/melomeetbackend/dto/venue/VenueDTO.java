package com.codecool.melomeetbackend.dto.venue;

public record VenueDTO(String name, WholeAddress wholeAddress, boolean isOpenAir, String venueId) {
}
