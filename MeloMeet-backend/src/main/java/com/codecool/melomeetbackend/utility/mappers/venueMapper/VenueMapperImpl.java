package com.codecool.melomeetbackend.utility.mappers.venueMapper;

import com.codecool.melomeetbackend.dto.venue.NewVenueDTO;
import com.codecool.melomeetbackend.dto.venue.VenueDTO;
import com.codecool.melomeetbackend.dto.venue.WholeAddress;
import com.codecool.melomeetbackend.model.Venue;
import org.springframework.stereotype.Service;

@Service
public class VenueMapperImpl implements VenueMapper{

    @Override
    public Venue newVenueDTOToVenue(NewVenueDTO newVenueDTO) {
        Venue newVenue = new Venue();
        WholeAddress wholeAddress= newVenueDTO.wholeAddress();

        newVenue.setOpenAir(newVenue.isOpenAir());
        newVenue.setName(newVenueDTO.name());
        newVenue.setCity(wholeAddress.city());
        newVenue.setAddress(wholeAddress.address());
        newVenue.setPostalCode(wholeAddress.postalCode());

        return newVenue;
    }

    @Override
    public VenueDTO venueToVenueDTO(Venue venue) {
        WholeAddress wholeAddress = new WholeAddress(venue.getAddress(), venue.getCity(),
                venue.getPostalCode());
        String venueId = venue.getVenueId().toString();
        VenueDTO venueDTO = new VenueDTO(venue.getName(), wholeAddress, venue.isOpenAir(), venueId);

        return venueDTO;
    }
}