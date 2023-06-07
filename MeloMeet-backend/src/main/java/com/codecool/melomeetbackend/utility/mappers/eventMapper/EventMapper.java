package com.codecool.melomeetbackend.utility.mappers.eventMapper;

import com.codecool.melomeetbackend.service.dto.events.ConcertEventDTO;
import com.codecool.melomeetbackend.service.dto.events.NewConcertEventDTO;
import com.codecool.melomeetbackend.model.eventModel.ConcertEvent;

public interface EventMapper {

    ConcertEvent mapDTOToEvent(ConcertEventDTO eventDTO);
    ConcertEventDTO mapConcertEventToConcertEventDTO(ConcertEvent concertEvent);
    ConcertEvent mapNewConcertEventDTOToCOncertEvent(NewConcertEventDTO newConcertEventDTO);
    //SimpleConcertEventDTO mapConcertEventToSimpleConcertEventDTO()
}
