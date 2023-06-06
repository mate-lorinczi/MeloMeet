package com.codecool.melomeetbackend.utility.mappers.venueMapper;

import com.codecool.melomeetbackend.service.dto.venue.NewVenueDTO;
import com.codecool.melomeetbackend.service.dto.venue.SimpleVenueDTO;
import com.codecool.melomeetbackend.service.dto.venue.VenueDTO;
import com.codecool.melomeetbackend.service.dto.venue.WholeAddress;
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
        WholeAddress wholeAddress = new WholeAddress(venue.getCity(),
                venue.getPostalCode(), venue.getAddress());
        String venueId = venue.getVenueId().toString();
        VenueDTO venueDTO = new VenueDTO(venue.getName(), wholeAddress, venue.isOpenAir(), venueId);

        return venueDTO;
    }

    @Override
    public SimpleVenueDTO venueToSimpleVenueDTO(Venue venue) {
        return new SimpleVenueDTO(venue.getName(), venue.getVenueId().toString());
    }
}
