package com.codecool.melomeetbackend.service.event;

import com.codecool.melomeetbackend.dto.events.EventDTO;
import com.codecool.melomeetbackend.model.Performer;
import com.codecool.melomeetbackend.model.eventModel.Event;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface EventService<T extends Event, S extends EventDTO> {

    T addNewEvent(S eventDTO);
    T findById(String id);
    List<T> findAll();
    Set<T> findByPerformer(String performer);
    boolean deleteById(String id);
    Set<Performer> findAllPerormersByEventId(String id);
}