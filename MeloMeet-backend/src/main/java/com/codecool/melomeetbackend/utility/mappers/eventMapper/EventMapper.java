package com.codecool.melomeetbackend.utility.mappers.eventMapper;

public interface EventMapper<T extends Event, S extends EventDTOs> {

    T mapDTOToEvent(S eventDTO);
}
