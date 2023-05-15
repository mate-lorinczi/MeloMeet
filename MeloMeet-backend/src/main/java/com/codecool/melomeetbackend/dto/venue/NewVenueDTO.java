package com.codecool.melomeetbackend.dto.venue;

public record NewVenueDTO(String name, WholeAddress wholeAddress,
                          boolean isOpenAir) {
}
