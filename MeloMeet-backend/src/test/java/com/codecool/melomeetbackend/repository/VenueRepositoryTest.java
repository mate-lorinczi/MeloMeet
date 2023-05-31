package com.codecool.melomeetbackend.repository;

import com.codecool.melomeetbackend.model.Venue;
import com.codecool.melomeetbackend.model.eventModel.ConcertEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringJUnitConfig
@DataJpaTest
class VenueRepositoryTest {

    @Autowired
    private VenueRepository venueRepository;
    private Venue venue;

    public static Stream<Arguments> provideVenuesForNullTests() {

        return Stream.of(

                Arguments.of(Venue.builder().name(null).address("Test " +
                        "Address").city("Test City").postalCode("Test Postal Code").build()),
                Arguments.of(Venue.builder().name("Test Name").address(null).city("Test City").postalCode("Test Postal Code").build()),
                Arguments.of(Venue.builder().name("Test Name").address("Test " +
                        "Address").city(null).postalCode("Test Postal Code").build()),
                Arguments.of(Venue.builder().name("Test Name").address("Test " +
                        "Address").city("Test City").postalCode(null).build())
        );
    }

    @BeforeEach
    void setUp() {
        venueRepository.deleteAll();

        this.venue =
                Venue.builder().name("Test Name").address("Test " +
                        "Address").city("Test City").postalCode("Test Postal Code").build();
    }

    @Test
    void testValuesSavedWithNonNullIsNonNull() {

        Venue savedVenue = venueRepository.save(venue);

        assertAll("Assert non null values",
                () -> assertNotNull(venue.getVenueId()),
                () -> assertNotNull(venue.getName()),
                () -> assertNotNull(venue.getCity()),
                () -> assertNotNull(venue.getAddress()),
                () -> assertNotNull(venue.getPostalCode())
                );
    }

    @ParameterizedTest
    @MethodSource("provideVenuesForNullTests")
    void testNonNullablesThrowsExceptionWhenSavedWithNull(Venue nullVenue) {

        assertAll("Assert that not nullable values throw exception when saved with null value",
                () -> assertThrows(DataIntegrityViolationException.class,
                        () -> venueRepository.save(nullVenue))
                );
    }

    @Test
    void testUUIDGeneration() {

        Venue savedVenue = venueRepository.save(venue);


        assertTrue(isValidUUID(savedVenue.getVenueId()));

    }

    private boolean isValidUUID(UUID uuid) {
        try {
            UUID.fromString(uuid.toString());

            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}