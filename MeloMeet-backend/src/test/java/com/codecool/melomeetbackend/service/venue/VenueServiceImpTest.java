package com.codecool.melomeetbackend.service.venue;

import com.codecool.melomeetbackend.model.Venue;
import com.codecool.melomeetbackend.repository.VenueRepository;
import com.codecool.melomeetbackend.service.dto.venue.NewVenueDTO;
import com.codecool.melomeetbackend.service.dto.venue.VenueDTO;
import com.codecool.melomeetbackend.service.dto.venue.WholeAddress;
import com.codecool.melomeetbackend.utility.mappers.venueMapper.VenueMapper;
import com.codecool.melomeetbackend.utility.mappers.venueMapper.VenueMapperImpl;
import jakarta.persistence.EntityExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VenueServiceImpTest {

    @Autowired
    private VenueService venueService;
    @MockBean(classes = VenueMapperImpl.class)
    private VenueMapper venueMapper;
    @MockBean
    private VenueRepository venueRepository;
    private VenueDTO venueDTO;
    private NewVenueDTO newVenueDTO;
    private Venue venue;

    @BeforeEach
    void setUp() {
        venueRepository.deleteAll();

        String venueID = "6c77ea0c-3bc8-4371-9a21-43b799de0f92";
        String name = "Test Name";
        WholeAddress wholeAddress = new WholeAddress("Budapest", "5600", "Alkotás Utca 26");
        newVenueDTO = new NewVenueDTO(name, wholeAddress, true);
        venueDTO = new VenueDTO(name, wholeAddress, true, venueID);

        venue =
                Venue.builder().venueId(UUID.fromString(venueID)).city(wholeAddress.city()).postalCode(wholeAddress.postalCode()).address(wholeAddress.address()).isOpenAir(true).build();
    }

    @Test
    void testAddNewVenueWithSuccess() {

        Mockito.when(venueRepository.save(venue)).thenReturn(venue);
        Mockito.when(venueMapper.venueToVenueDTO(venue)).thenReturn(venueDTO);
        Mockito.when(venueRepository.existsByAddressAndCityAndPostalCode(Mockito.any(),
                Mockito.any(), Mockito.any())).thenReturn(false);

        VenueDTO result = venueService.addNewVenue(newVenueDTO);

        assertAll(() -> assertEquals("Test Name", venueDTO.name()),
                () -> assertEquals("Budapest", venueDTO.wholeAddress().city()),
                () -> assertEquals("5600", venueDTO.wholeAddress().postalCode()),
                () -> assertEquals("Alkotás Utca 26", venueDTO.wholeAddress().address()),
                () -> assertEquals("6c77ea0c-3bc8-4371-9a21-43b799de0f92",
                        venueDTO.venueId()),
                () -> assertTrue(venueDTO.isOpenAir())
                );
    }

    @Test
     void testAddNewVenueWithAlreadyExisting() {

        Mockito.when(venueRepository.existsByAddressAndCityAndPostalCode(Mockito.any(),
                Mockito.any(), Mockito.any())).thenReturn(true);

        EntityExistsException entityExistsException = assertThrows(EntityExistsException.class, () -> venueService.addNewVenue(newVenueDTO));
        assertEquals("Venue with the same address already exists!", entityExistsException.getMessage());
    }
}