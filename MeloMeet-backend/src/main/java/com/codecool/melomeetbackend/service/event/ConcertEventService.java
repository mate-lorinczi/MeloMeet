package com.codecool.melomeetbackend.service.event;

import com.codecool.melomeetbackend.dto.events.ConcertEventDTO;
import com.codecool.melomeetbackend.dto.events.NewConcertEventDTO;
import com.codecool.melomeetbackend.model.Performer;
import com.codecool.melomeetbackend.model.eventModel.ConcertEvent;

import java.util.List;
import java.util.Set;

public interface ConcertEventService {

    ConcertEventDTO addNewEvent(NewConcertEventDTO concertEventDTOs);
    ConcertEventDTO findById(String id);
    List<ConcertEventDTO> findAll();
    Set<ConcertEventDTO> findByPerformer(String performer);
    boolean deleteById(String id);
    Set<Performer> findAllPerormersByEventId(String id);
}
