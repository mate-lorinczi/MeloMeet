package com.codecool.melomeetbackend.controller;

import com.codecool.melomeetbackend.service.dto.venue.NewVenueDTO;
import com.codecool.melomeetbackend.service.dto.venue.VenueDTO;
import com.codecool.melomeetbackend.service.venue.VenueService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/venues")
public class VenueController {

    private final VenueService venueService;

    @Autowired
    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @PostMapping("")
    public ResponseEntity<?> addNewVenue(@RequestBody NewVenueDTO newVenueDTO) {

        VenueDTO venueDTO = venueService.addNewVenue(newVenueDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(venueDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllVenues() {
        Set<VenueDTO> venueDTOSet = venueService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(venueDTOSet);
    }

    @GetMapping("")
    public ResponseEntity<?> getVenuesByNameFraction(@RequestParam String name) {

        Set<VenueDTO> venueDTOSet = venueService.findAllContainingString(name);

        return ResponseEntity.status(HttpStatus.OK).body(venueDTOSet);
    }

    @GetMapping("/{venueId}")
    public ResponseEntity<?> getVenueById(@PathVariable String venueId) {

        VenueDTO venueDTO = venueService.findById(venueId);

        return ResponseEntity.status(HttpStatus.OK).body(venueDTO);
    }
}
