package com.codecool.melomeetbackend.utility.mappers;

import com.codecool.melomeetbackend.dto.events.EventDTO;
import com.codecool.melomeetbackend.model.eventModel.Event;

public interface EventMapper<T extends Event, S extends EventDTO> {

    T mapDTOToEvent(S eventDTO);
}
