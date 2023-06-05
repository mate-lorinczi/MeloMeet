package com.codecool.melomeetbackend.utility.mappers.venueMapper;

import com.codecool.melomeetbackend.service.dto.venue.NewVenueDTO;
import com.codecool.melomeetbackend.service.dto.venue.SimpleVenueDTO;
import com.codecool.melomeetbackend.service.dto.venue.VenueDTO;
import com.codecool.melomeetbackend.model.Venue;

public interface VenueMapper {

    Venue newVenueDTOToVenue(NewVenueDTO newVenueDTO);
    VenueDTO venueToVenueDTO(Venue venue);
    SimpleVenueDTO venueToSimpleVenueDTO(Venue venue);
}
