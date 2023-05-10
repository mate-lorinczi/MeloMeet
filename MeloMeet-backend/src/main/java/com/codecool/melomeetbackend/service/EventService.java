package com.codecool.melomeetbackend.service;

import com.codecool.melomeetbackend.dto.EventDTO;
import com.codecool.melomeetbackend.model.Performer;
import com.codecool.melomeetbackend.model.eventModel.Event;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public interface EventService<T extends Event> {

    T addNewEvent(EventDTO eventDTO);
    T findById(UUID id);
    Set<T> findAll();
    Set<T> findByPerformer(Performer performer);
    boolean deleteById(UUID id);
}
