package com.codecool.melomeetbackend.utility.mappers.venueMapper;

import com.codecool.melomeetbackend.model.Venue;
import com.codecool.melomeetbackend.service.dto.venue.NewVenueDTO;
import com.codecool.melomeetbackend.service.dto.venue.WholeAddress;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class VenueMapperImplTest {

    @Autowired
    private VenueMapper venueMapper;

    @Test
    void testNewVenueDTOToVenue() {
        String name = "Test Name";
        String city = "Test City";
        String address = "Test Address";
        String postalCode = "Test Postal Code";
        boolean isOpenAir = true;
        WholeAddress wholeAddress = new WholeAddress(city, postalCode, address);
        NewVenueDTO newVenueDTO = new NewVenueDTO(name, wholeAddress, isOpenAir);

        Venue venue = venueMapper.newVenueDTOToVenue(newVenueDTO);

        assertAll(
                () -> assertEquals(name, venue.getName()),
                () -> assertEquals(city, venue.getCity()),
                () -> assertEquals(address, venue.getAddress()),
                () -> assertEquals(postalCode, venue.getPostalCode()),
                () -> assertEquals(isOpenAir, venue.isOpenAir())
        );
    }
}