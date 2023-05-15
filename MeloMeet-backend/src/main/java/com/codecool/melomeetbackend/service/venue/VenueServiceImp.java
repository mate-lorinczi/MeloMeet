package com.codecool.melomeetbackend.service.venue;

import com.codecool.melomeetbackend.dto.venue.NewVenueDTO;
import com.codecool.melomeetbackend.dto.venue.VenueDTO;
import com.codecool.melomeetbackend.dto.venue.WholeAddress;
import com.codecool.melomeetbackend.model.Venue;
import com.codecool.melomeetbackend.repository.VenueRepository;
import com.codecool.melomeetbackend.service.venue.VenueService;
import com.codecool.melomeetbackend.utility.mappers.venueMapper.VenueMapper;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
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
    public VenueDTO findById(UUID uuid) {
        return null;
    }

    @Override
    public Venue addNewVenue(NewVenueDTO newVenueDTO) {

        WholeAddress wholeAddress = newVenueDTO.wholeAddress();

        boolean addressAlreadyUsed =
                venueRepository.existsByAddressAndCityAndPostalCode(wholeAddress.address(),
                        wholeAddress.city(), wholeAddress.postalCode());

        if (addressAlreadyUsed) {
            throw new EntityExistsException("Venue with the same address already exists!");
        }

        Venue venue = venueMapper.newVenueDTOToVenue(newVenueDTO);

        return venueRepository.save(venue);
    }

    @Override
    public Set<VenueDTO> findAll() {
        return null;
    }
}
