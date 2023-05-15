package com.codecool.melomeetbackend.utility.mappers.venueMapper;

import com.codecool.melomeetbackend.dto.venue.NewVenueDTO;
import com.codecool.melomeetbackend.model.Venue;

public interface VenueMapper {

    Venue newVenueDTOToVenue(NewVenueDTO newVenueDTO);
}
