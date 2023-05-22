package com.codecool.melomeetbackend.repository;

import com.codecool.melomeetbackend.model.Performer;
import com.codecool.melomeetbackend.model.User;
import com.codecool.melomeetbackend.model.Venue;
import com.codecool.melomeetbackend.model.eventModel.ConcertEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringJUnitConfig
@DataJpaTest
class ConcertEventRepositoryTest {

    @Autowired
    private ConcertEventRepository concertEventRepository;
    @Autowired
    private VenueRepository venueRepository;
    @Autowired
    private PerformerRepository performerRepository;
    @Autowired
    private UserRepository userRepository;
    private User user;
    private Performer performer;
    private Venue venue;

    @BeforeEach
    void setUp() {
        concertEventRepository.deleteAll();
        venueRepository.deleteAll();
        performerRepository.deleteAll();
        userRepository.deleteAll();

        this.user = User.builder().password("password").email("email").username("username").build();
        userRepository.save(user);

        this.performer = new Performer();
        performerRepository.save(performer);

        this.venue = new Venue();
        venueRepository.save(venue);
    }

    @Test
    void testUUIDGeneration() {
        ConcertEvent concertEvent =
                ConcertEvent.builder().createdBy(user).performers(Set.of(performer)).venue(venue).build();

        concertEventRepository.save(concertEvent);

        System.out.println(concertEvent.getEventId());

        assertTrue(isValidUUID(concertEvent.getEventId()));

    }

    @Test
    void testNonNullAttributesReturnNonNull() {

        ConcertEvent concertEvent =
                ConcertEvent.builder().createdBy(user).performers(Set.of(performer)).venue(venue).build();

        ConcertEvent savedConcertEvent = concertEventRepository.save(concertEvent);

        assertAll("Grouped assertions of non nulls of ConcertEvent",
                () -> assertNotNull(savedConcertEvent.getEventId()),
                () -> assertNotNull(savedConcertEvent.getCreatedBy()),
                () -> assertNotNull(savedConcertEvent.getPerformers()),
                () -> assertNotNull(savedConcertEvent.getVenue())
                );
    }

    @Test
    void testNonNullAttributesThrowsErrorWhenSavedWithNull() {
        ConcertEvent concertEvent = new ConcertEvent();

        Exception e= assertThrows(Exception.class, () -> concertEventRepository.save(concertEvent));

        System.out.println(e.getClass());
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