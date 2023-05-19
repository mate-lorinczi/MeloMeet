package com.codecool.melomeetbackend.service.venue;

import com.codecool.melomeetbackend.service.dto.venue.NewVenueDTO;
import com.codecool.melomeetbackend.service.dto.venue.VenueDTO;

import java.util.Set;

public interface VenueService {

    VenueDTO findById(String uuid);
    VenueDTO addNewVenue(NewVenueDTO newVenueDTO);
    Set<VenueDTO> findAll();
    Set<VenueDTO> findAllContainingString(String searchName);

}
