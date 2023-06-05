package com.codecool.melomeetbackend.controller;

import com.codecool.melomeetbackend.model.eventModel.ConcertEvent;
import com.codecool.melomeetbackend.service.dto.events.ConcertEventDTO;
import com.codecool.melomeetbackend.service.dto.events.NewConcertEventDTO;
import com.codecool.melomeetbackend.service.event.ConcertEventService;
import com.codecool.melomeetbackend.service.event.ConcertEventServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/events")
public class ConcertEventController {

    private final ConcertEventService concertEventServiceImpl;

    @Autowired
    public ConcertEventController(ConcertEventService concertEventServiceImpl) {
        this.concertEventServiceImpl = concertEventServiceImpl;
    }

    @PostMapping("")
    public ResponseEntity<?> addNewConcertEvent(@RequestBody NewConcertEventDTO concertEventDTO) {
        System.out.println(concertEventDTO);
        ConcertEventDTO concertEvent = concertEventServiceImpl.addNewEvent(concertEventDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(concertEvent);

    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllConcertEvents() {
        List<ConcertEventDTO> concertEvents = concertEventServiceImpl.findAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(concertEvents);
    }

    @GetMapping("")
    public ResponseEntity<?> getConcertEventsByPerformer(@RequestParam("performer") String performer) {

        Set<ConcertEventDTO> concerts = concertEventServiceImpl.findByPerformer(performer);

        if (concerts.size() < 1) {
            return ResponseEntity.status(HttpStatus.OK).body("No event found for " + performer +
                    " ");
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(concerts);
    }

    @GetMapping("/{concertEventId}")
    public ResponseEntity<?> findConcertById(@PathVariable String concertEventId) {
        ConcertEventDTO concertEvent =
                concertEventServiceImpl.getConcertEvenDTOtById(concertEventId);

        return ResponseEntity.status(HttpStatus.OK).body(concertEvent);
    }

    @DeleteMapping("/{concertEventId}")
    public ResponseEntity<?> deleteConcertEventById(@PathVariable String concertEventId) {
        concertEventServiceImpl.deleteById(concertEventId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Concert with id: " + concertEventId + " deleted");
    }
}
