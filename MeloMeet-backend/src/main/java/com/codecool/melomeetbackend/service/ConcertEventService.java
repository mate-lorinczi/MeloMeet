package com.codecool.melomeetbackend.service;

import com.codecool.melomeetbackend.dto.ConcertEventDTO;
import com.codecool.melomeetbackend.dto.EventDTO;
import com.codecool.melomeetbackend.model.Performer;
import com.codecool.melomeetbackend.model.eventModel.ConcertEvent;
import com.codecool.melomeetbackend.repository.ConcertEventRepository;
import com.codecool.melomeetbackend.service.mappers.ConcertEventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        ConcertEvent concertEvent = concertEventMapper.mapDTOToEvent(eventDTO);
        ConcertEvent savedConcertEvent = concertEventRepository.save(concertEvent);

        return savedConcertEvent;
    }

    @Override
    public ConcertEvent findById(UUID id) {
        return null;
    }

    @Override
    public Set<ConcertEvent> findAll() {
        return null;
    }

    @Override
    public Set<ConcertEvent> findByPerformer(Performer performer) {
        return null;
    }

    @Override
    public boolean deleteById(UUID id) {
        return false;
    }
}
