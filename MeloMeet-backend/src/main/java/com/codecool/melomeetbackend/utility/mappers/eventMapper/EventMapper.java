package com.codecool.melomeetbackend.utility.mappers.eventMapper;

import com.codecool.melomeetbackend.dto.events.ConcertEventDTO;
import com.codecool.melomeetbackend.model.eventModel.ConcertEvent;

public interface EventMapper {

    ConcertEvent mapDTOToEvent(ConcertEventDTO eventDTO);
    ConcertEventDTO mapConcertEventToConcertEventDTO(ConcertEvent concertEvent);
}
