package com.codecool.melomeetbackend.controller;

import com.codecool.melomeetbackend.model.eventModel.ConcertEvent;
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

    private final ConcertEventServiceImpl concertEventServiceImpl;

    @Autowired
    public ConcertEventController(ConcertEventServiceImpl concertEventServiceImpl) {
        this.concertEventServiceImpl = concertEventServiceImpl;
    }

    @PostMapping("")
    public ResponseEntity<?> addNewConcertEvent(@RequestBody ConcertEventDTOs concertEventDTOs) {

        try {
            ConcertEvent concertEvent = concertEventServiceImpl.addNewEvent(concertEventDTOs);
            return ResponseEntity.status(HttpStatus.CREATED).body(concertEvent);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request.");
        }

    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllConcertEvents() {
        try {
            List<ConcertEvent> concertEvents = concertEventServiceImpl.findAll();
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(concertEvents);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request!");
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getConcertEventsByPerformer(@RequestParam("performer") String performer) {

        try {
            Set<ConcertEvent> concerts = concertEventServiceImpl.findByPerformer(performer);

            if(concerts.size() < 1) {
                return ResponseEntity.status(HttpStatus.OK).body("No event found for " + performer + " ");
            }

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(concerts);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{concertEventId}")
    public ResponseEntity<?> findConcertById(@PathVariable String concertEventId) {
        try {
            ConcertEvent concertEvent = concertEventServiceImpl.findById(concertEventId);

            return ResponseEntity.status(HttpStatus.OK).body(concertEvent);
        } catch (EntityNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Concert not found.");
        } catch (Exception e) {
            return  ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{concertEventId}")
    public ResponseEntity<?> deleteConcertEventById(@PathVariable String concertEventId) {
        try {
            concertEventServiceImpl.deleteById(concertEventId);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Concert with id: " + concertEventId + " deleted");
        } catch (EntityNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Concert not found");
        }
    }
}
