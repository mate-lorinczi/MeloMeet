package com.codecool.melomeetbackend.service.event;

import com.codecool.melomeetbackend.service.dto.events.ConcertEventDTO;
import com.codecool.melomeetbackend.service.dto.events.NewConcertEventDTO;
import com.codecool.melomeetbackend.model.Performer;
import com.codecool.melomeetbackend.model.eventModel.ConcertEvent;
import com.codecool.melomeetbackend.repository.ConcertEventRepository;
import com.codecool.melomeetbackend.repository.PerformerRepository;
import com.codecool.melomeetbackend.utility.mappers.eventMapper.ConcertEventMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
//TODO: Tagolni a streameket!
@Service
public class ConcertEventServiceImpl implements ConcertEventService {
    private final ConcertEventRepository concertEventRepository;
    private final ConcertEventMapper concertEventMapper;
    private final PerformerRepository performerRepository;

    @Autowired
    public ConcertEventServiceImpl(ConcertEventRepository concertEventRepository,
                                   ConcertEventMapper concertEventMapper,
                                   PerformerRepository performerRepository
    ) {
        this.concertEventRepository = concertEventRepository;
        this.concertEventMapper = concertEventMapper;
        this.performerRepository = performerRepository;
    }

    @Override
    public ConcertEventDTO addNewEvent(NewConcertEventDTO eventDTO) {

        if(eventDTO.endDateAndTime().isBefore(eventDTO.startDateAndTime())) {
            throw new IllegalArgumentException("End time cannot be before start time");
        }

        ConcertEvent concertEvent = concertEventMapper.mapNewConcertEventDTOToCOncertEvent(eventDTO);

        ConcertEvent savedConcertEvent;

        try {
            savedConcertEvent = concertEventRepository.save(concertEvent);
        } catch (Exception e) {
            throw e;
        }

        ConcertEventDTO concertEventDTO =
                concertEventMapper.mapConcertEventToConcertEventDTO(savedConcertEvent);

        return concertEventDTO;
    }

    @Override
    public ConcertEventDTO getConcertEvenDTOtById(String id) {
        ConcertEvent concertEvent = this.findById(id);

        return concertEventMapper.mapConcertEventToConcertEventDTO(concertEvent);
    }

    private ConcertEvent findById(String id) {
        return concertEventRepository.findById(UUID.fromString(id)).orElseThrow(() -> new EntityNotFoundException("Event with id: " + id + " not found"));
    }

    @Override
    public List<ConcertEventDTO> findAll() {
        var concertEvents =
                concertEventRepository
                        .findAll()
                        .stream()
                        .map(concertEventMapper::mapConcertEventToConcertEventDTO)
                        .toList();

        return concertEvents;
    }

    @Override
    public Set<ConcertEventDTO> findByPerformer(String performer) {
        Performer performerFromString =
                performerRepository.findByName(performer).orElseThrow(() -> new EntityNotFoundException("Performer " +
                        "with name " + performer + " not found!"));
        var concerts =
                concertEventRepository.findConcertEventsByPerformersContains(performerFromString).stream().map(concertEventMapper::mapConcertEventToConcertEventDTO).collect(Collectors.toSet());

        return concerts;
    }

    @Override
    public boolean deleteById(String id) {
        int affectedRows = concertEventRepository.deleteByEventId(UUID.fromString(id));

        if(affectedRows < 1) {
            throw new EntityNotFoundException("Entity with id " + id + " not found");
        } else {
            return true;
        }
    }

    @Override
    public Set<Performer> findAllPerormersByEventId(String id) {
        ConcertEvent concertEvent = this.findById(id);

        return concertEvent.getPerformers();
    }
}
