package com.codecool.melomeetbackend.service.venue;

import com.codecool.melomeetbackend.model.Venue;
import com.codecool.melomeetbackend.repository.VenueRepository;
import com.codecool.melomeetbackend.service.dto.venue.NewVenueDTO;
import com.codecool.melomeetbackend.service.dto.venue.VenueDTO;
import com.codecool.melomeetbackend.service.dto.venue.WholeAddress;
import com.codecool.melomeetbackend.utility.mappers.venueMapper.VenueMapper;
import com.codecool.melomeetbackend.utility.mappers.venueMapper.VenueMapperImpl;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.Set;
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
    private Set<VenueDTO> venueDTOSet;

    @BeforeEach
    void setUp() {
        venueRepository.deleteAll();

        String venueID1 = "6c77ea0c-3bc8-4371-9a21-43b799de0f92";
        String name = "Test Name";
        WholeAddress wholeAddress = new WholeAddress("Budapest", "5600", "Alkotás Utca 26");
        newVenueDTO = new NewVenueDTO(name, wholeAddress, true);
        venueDTO = new VenueDTO(name, wholeAddress, true, venueID1);

        venue =
                Venue.builder().venueId(UUID.fromString(venueID1)).city(wholeAddress.city()).postalCode(wholeAddress.postalCode()).address(wholeAddress.address()).isOpenAir(true).build();
    }

    @Test
    void testAddNewVenueWithSuccess() {

        Mockito.when(venueRepository.save(venue)).thenReturn(venue);
        Mockito.when(venueMapper.venueToVenueDTO(venue)).thenReturn(venueDTO);
        Mockito.when(venueMapper.newVenueDTOToVenue(newVenueDTO)).thenReturn(venue);
        Mockito.when(venueRepository.existsByAddressAndCityAndPostalCode(Mockito.any(),
                Mockito.any(), Mockito.any())).thenReturn(false);

        VenueDTO result = venueService.addNewVenue(newVenueDTO);

        assertAll(() -> assertEquals("Test Name", result.name()),
                () -> assertEquals("Budapest", result.wholeAddress().city()),
                () -> assertEquals("5600", result.wholeAddress().postalCode()),
                () -> assertEquals("Alkotás Utca 26", result.wholeAddress().address()),
                () -> assertEquals("6c77ea0c-3bc8-4371-9a21-43b799de0f92",
                        result.venueId()),
                () -> assertTrue(result.isOpenAir())
                );
        Mockito.verify(venueRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(venueMapper, Mockito.times(1)).venueToVenueDTO(Mockito.any());
        Mockito.verify(venueRepository, Mockito.times(1)).existsByAddressAndCityAndPostalCode(Mockito.any(), Mockito.any(), Mockito.any());
    }

    @Test
     void testAddNewVenueWithAlreadyExisting() {

        Mockito.when(venueRepository.existsByAddressAndCityAndPostalCode(Mockito.any(),
                Mockito.any(), Mockito.any())).thenReturn(true);

        EntityExistsException entityExistsException = assertThrows(EntityExistsException.class, () -> venueService.addNewVenue(newVenueDTO));
        assertEquals("Venue with the same address already exists!", entityExistsException.getMessage());
    }

    @Test
    void testFindByIdWithCorrectId() {

        Mockito.when(venueRepository.findById(UUID.fromString("6c77ea0c-3bc8-4371-9a21" +
                "-43b799de0f92"))).thenReturn(Optional.of(venue));
        Mockito.when(venueMapper.venueToVenueDTO(venue)).thenReturn(venueDTO);

        var result = venueService.findById("6c77ea0c-3bc8-4371-9a21" +
                "-43b799de0f92");

        assertAll(() -> assertEquals("Test Name", result.name()),
                () -> assertEquals("Budapest", result.wholeAddress().city()),
                () -> assertEquals("5600", result.wholeAddress().postalCode()),
                () -> assertEquals("Alkotás Utca 26", result.wholeAddress().address()),
                () -> assertEquals("6c77ea0c-3bc8-4371-9a21-43b799de0f92",
                        result.venueId()),
                () -> assertTrue(result.isOpenAir())
        );
    }

    @Test
    void testFindByIdWithIncorrectId() {

        String randomId = UUID.randomUUID().toString();
        String msg = "Venue with id " + randomId + " not found!";
        Mockito.when(venueRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        EntityNotFoundException entityNotFoundException =
                assertThrows(EntityNotFoundException.class,
                () -> venueService.findById(randomId));

        assertEquals(msg, entityNotFoundException.getMessage());
    }


}