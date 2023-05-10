package com.codecool.melomeetbackend.service.mappers;

import com.codecool.melomeetbackend.dto.EventDTO;
import com.codecool.melomeetbackend.model.eventModel.Event;

public interface EventMapper<T extends Event, S extends EventDTO> {

    T mapDTOToEvent(S eventDTO);
}
