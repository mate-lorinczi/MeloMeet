package com.codecool.melomeetbackend.service;

import com.codecool.melomeetbackend.dto.events.ConcertEventDTO;
import com.codecool.melomeetbackend.model.Performer;
import com.codecool.melomeetbackend.model.eventModel.ConcertEvent;
import com.codecool.melomeetbackend.repository.ConcertEventRepository;
import com.codecool.melomeetbackend.service.mappers.ConcertEventMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
@Service
public class ConcertEventService implements EventService<ConcertEvent, ConcertEventDTO>{
    private final ConcertEventRepository concertEventRepository;
    private final ConcertEventMapper concertEventMapper;

    @Autowired
    public ConcertEventService(ConcertEventRepository concertEventRepository,
                               ConcertEventMapper concertEventMapper) {
        this.concertEventRepository = concertEventRepository;
        this.concertEventMapper = concertEventMapper;
    }

    @Override
    public ConcertEvent addNewEvent(ConcertEventDTO eventDTO) {

        if(eventDTO.getEndDateAndTime().isBefore(eventDTO.getStartDateAndTime())) {
            throw new IllegalArgumentException("End time cannot be before start time");
        }

        ConcertEvent concertEvent = concertEventMapper.mapDTOToEvent(eventDTO);

        ConcertEvent savedConcertEvent;

        try {
            savedConcertEvent = concertEventRepository.save(concertEvent);
        } catch (Exception e) {
            throw e;
        }

        return savedConcertEvent;
    }

    @Override
    public ConcertEvent findById(String id) {

        return concertEventRepository.findById(UUID.fromString(id)).orElseThrow(() -> new EntityNotFoundException("Event with id: " + id + " not found"));
    }

    @Override
    public List<ConcertEvent> findAll() {

        return concertEventRepository.findAll();
    }

    @Override
    public Set<ConcertEvent> findByPerformer(Performer performer) {
        Set<ConcertEvent> concerts =
                concertEventRepository.findConcertEventsByPerformersContains(performer);

        if(concerts.size() < 1) {
            throw new EntityNotFoundException("Event with performer: " + performer + " not found");
        }

        return concerts;
    }

    @Override
    public boolean deleteById(String id) {
        int affectedRows = concertEventRepository.deleteByEventId(UUID.fromString(id));

        if(affectedRows < 1) {
            throw new EntityNotFoundException("Entity with id " + id + " not found");
        } else {
            return true;
        }
    }
}
