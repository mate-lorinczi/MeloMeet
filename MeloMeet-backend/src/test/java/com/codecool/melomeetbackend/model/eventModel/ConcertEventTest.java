package com.codecool.melomeetbackend.model.eventModel;

import com.codecool.melomeetbackend.model.Performer;
import com.codecool.melomeetbackend.model.Style;
import com.codecool.melomeetbackend.model.Venue;
import com.codecool.melomeetbackend.repository.ConcertEventRepository;
import com.codecool.melomeetbackend.repository.PerformerRepository;
import com.codecool.melomeetbackend.repository.StyleRepository;
import com.codecool.melomeetbackend.repository.VenueRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ConcertEventTest {

    @Autowired
    private ConcertEventRepository concertEventRepository;
    @Autowired
    private VenueRepository venueRepository;
    @Autowired
    private PerformerRepository performerRepository;
    @Autowired
    private StyleRepository styleRepository;

    @Test
    public void testConcertEventEntity(){

        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.plusHours(2);
        Venue venue = venueRepository.save(new Venue());
        Style style = styleRepository.save(new Style());
        Set<Style> styles = Set.of(style);
        Performer performer = performerRepository.save(new Performer());
        Set<Performer> performerSet = Set.of(performer);

        ConcertEvent concertEvent = new ConcertEvent();
        concertEvent.setEndDateAndTime(endTime);
        concertEvent.setStartDateAndTime(startTime);
        concertEvent.setStyles(styles);
        concertEvent.setPerformers(performerSet);
        concertEvent.setVenue(venue);

        ConcertEvent savedConcertEvent = concertEventRepository.save(concertEvent);

        assertAll(() -> {
            assertEquals(savedConcertEvent.getStartDateAndTime(),
                    concertEvent.getStartDateAndTime());
            assertEquals(savedConcertEvent.getEndDateAndTime(), concertEvent.getEndDateAndTime());
        });
    }
}