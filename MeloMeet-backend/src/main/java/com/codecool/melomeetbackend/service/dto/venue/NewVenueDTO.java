package com.codecool.melomeetbackend.service.dto.venue;

public record NewVenueDTO(String name, WholeAddress wholeAddress,
                          boolean isOpenAir) {
}
