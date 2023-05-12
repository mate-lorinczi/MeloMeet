package com.codecool.melomeetbackend.controller;

import com.codecool.melomeetbackend.dto.events.ConcertEventDTO;
import com.codecool.melomeetbackend.model.eventModel.ConcertEvent;
import com.codecool.melomeetbackend.service.ConcertEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/events/")
public class ConcertEventController {

    private final ConcertEventService concertEventService;

    @Autowired
    public ConcertEventController(ConcertEventService concertEventService) {
        this.concertEventService = concertEventService;
    }

    @PostMapping("concertEvent")
    public ResponseEntity<?> addNewConcertEvent(@RequestBody ConcertEventDTO concertEventDTO) {

        try {
            ConcertEvent concertEvent = concertEventService.addNewEvent(concertEventDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(concertEvent);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request.");
        }

    }

    @GetMapping("")
    public ResponseEntity<?> getAllConcertEvents() {
        try {
            List<ConcertEvent> concertEvents = concertEventService.findAll();
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(concertEvents);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request!");
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getConcertEventsByPerformer(@RequestParam String performer) {
        try {
            Set<ConcertEvent> concerts = concertEventService.findByPerformer(performer);
        }
    }
}
