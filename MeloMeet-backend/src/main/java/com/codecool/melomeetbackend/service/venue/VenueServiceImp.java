package com.codecool.melomeetbackend.service.venue;

import com.codecool.melomeetbackend.service.dto.venue.NewVenueDTO;
import com.codecool.melomeetbackend.service.dto.venue.VenueDTO;
import com.codecool.melomeetbackend.service.dto.venue.WholeAddress;
import com.codecool.melomeetbackend.model.Venue;
import com.codecool.melomeetbackend.repository.VenueRepository;
import com.codecool.melomeetbackend.utility.mappers.venueMapper.VenueMapper;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VenueServiceImp implements VenueService {

    private final VenueRepository venueRepository;
    private final VenueMapper venueMapper;

    @Autowired
    public VenueServiceImp(VenueRepository venueRepository, VenueMapper venueMapper) {
        this.venueRepository = venueRepository;
        this.venueMapper = venueMapper;
    }

    @Override
    public VenueDTO findById(String uuid) {
        Venue venue =
                venueRepository.findById(UUID.fromString(uuid)).orElseThrow(() -> new EntityNotFoundException("Venue with id " + uuid + " not found!"));

        VenueDTO venueDTO = venueMapper.venueToVenueDTO(venue);

        return venueDTO;
    }

    @Override
    public VenueDTO addNewVenue(NewVenueDTO newVenueDTO) {

        WholeAddress wholeAddress = newVenueDTO.wholeAddress();

        boolean addressAlreadyUsed =
                venueRepository.existsByAddressAndCityAndPostalCode(wholeAddress.address(),
                        wholeAddress.city(), wholeAddress.postalCode());

        if (addressAlreadyUsed) {
            throw new EntityExistsException("Venue with the same address already exists!");
        }

        Venue venue = venueMapper.newVenueDTOToVenue(newVenueDTO);
        Venue savedVenue = venueRepository.save(venue);

        VenueDTO venueDTO = venueMapper.venueToVenueDTO(savedVenue);

        return venueDTO;
    }

    @Override
    public Set<VenueDTO> findAll() {

        Set<VenueDTO> venueDTOSet =
                venueRepository.findAll().stream().map(venueMapper::venueToVenueDTO).collect(Collectors.toSet());

        return venueDTOSet;
    }

    @Override
    public Set<VenueDTO> findAllContainingString(String searchName) {

        Set<VenueDTO> venues =
                venueRepository.findAllByNameContaining(searchName).stream().map(venueMapper::venueToVenueDTO).collect(Collectors.toSet());

        if(venues.size() < 1) {
            return Set.of();
        }

        return venues;
    }
}
