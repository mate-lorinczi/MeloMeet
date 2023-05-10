package com.codecool.melomeetbackend.service;

import com.codecool.melomeetbackend.dto.EventDTO;
import com.codecool.melomeetbackend.model.Performer;
import com.codecool.melomeetbackend.model.eventModel.ConcertEvent;
import com.codecool.melomeetbackend.repository.ConcertEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
@Service
public class ConcertEventService implements EventService<ConcertEvent>{
    private final ConcertEventRepository concertEventRepository;

    @Autowired
    public ConcertEventService(ConcertEventRepository concertEventRepository) {
        this.concertEventRepository = concertEventRepository;
    }

    @Override
    public ConcertEvent addNewEvent(EventDTO eventDTO) {
        return null;
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
