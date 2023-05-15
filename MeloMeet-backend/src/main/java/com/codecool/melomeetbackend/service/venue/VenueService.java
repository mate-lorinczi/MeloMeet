package com.codecool.melomeetbackend.service.venue;

import com.codecool.melomeetbackend.dto.venue.NewVenueDTO;
import com.codecool.melomeetbackend.dto.venue.VenueDTO;
import com.codecool.melomeetbackend.model.Venue;

import java.util.Set;
import java.util.UUID;

public interface VenueService {

    VenueDTO findById(String uuid);
    Venue addNewVenue(NewVenueDTO newVenueDTO);
    Set<VenueDTO> findAll();


}
