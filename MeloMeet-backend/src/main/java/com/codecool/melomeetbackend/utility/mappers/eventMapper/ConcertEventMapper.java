package com.codecool.melomeetbackend.utility.mappers.eventMapper;

import com.codecool.melomeetbackend.model.Venue;
import com.codecool.melomeetbackend.repository.VenueRepository;
import com.codecool.melomeetbackend.service.dto.events.ConcertEventDTO;
import com.codecool.melomeetbackend.service.dto.events.NewConcertEventDTO;
import com.codecool.melomeetbackend.model.Performer;
import com.codecool.melomeetbackend.model.Style;
import com.codecool.melomeetbackend.model.User;
import com.codecool.melomeetbackend.model.eventModel.ConcertEvent;
import com.codecool.melomeetbackend.repository.PerformerRepository;
import com.codecool.melomeetbackend.repository.StyleRepository;
import com.codecool.melomeetbackend.repository.UserRepository;
import com.codecool.melomeetbackend.service.dto.venue.SimpleVenueDTO;
import com.codecool.melomeetbackend.utility.mappers.userMapper.UserMapper;
import com.codecool.melomeetbackend.utility.mappers.venueMapper.VenueMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ConcertEventMapper implements EventMapper {

    private final PerformerRepository performerRepository;
    private final StyleRepository styleRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final VenueRepository venueRepository;
    private final VenueMapper venueMapper;

    @Autowired
    public ConcertEventMapper(PerformerRepository performerRepository,
                              StyleRepository styleRepository,
                              UserRepository userRepository,
                              UserMapper userMapper,
                              VenueRepository venueRepository,
                              VenueMapper venueMapper
    ) {
        this.performerRepository = performerRepository;
        this.styleRepository = styleRepository;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.venueRepository = venueRepository;
        this.venueMapper = venueMapper;
    }

    @Override
    public ConcertEvent mapDTOToEvent(ConcertEventDTO eventDTO) {
        LocalDateTime startDateAndTime = eventDTO.startDateAndTime();
        LocalDateTime endDateAndTime = eventDTO.endDateAndTime();
        Set<Performer> performers = eventDTO.performers();
        Set<Style> styleSet = eventDTO.styles();
        User creator = getUser(eventDTO.createdBy().userId());

        ConcertEvent concertEvent = new ConcertEvent();
        concertEvent.setStyles(styleSet);
        concertEvent.setPerformers(performers);
        concertEvent.setStartDateAndTime(startDateAndTime);
        concertEvent.setEndDateAndTime(endDateAndTime);

        return concertEvent;
    }

    @Override
    public ConcertEventDTO mapConcertEventToConcertEventDTO(ConcertEvent concertEvent) {
        UUID eventId = concertEvent.getEventId();
        String name =
                "Concert of [" + concertEvent.getPerformers()
                        .stream()
                        .map(Performer::getName)
                        .collect(Collectors.joining(", ")) + "] at " + concertEvent.getVenue().getName();
        SimpleVenueDTO simpleVenueDTO = venueMapper.venueToSimpleVenueDTO(concertEvent.getVenue());
        ConcertEventDTO concertEventDTO = new ConcertEventDTO(eventId.toString(),
                concertEvent.getStartDateAndTime(), concertEvent.getEndDateAndTime(),
                concertEvent.getPerformers(),
                userMapper.userEntityToUserDTO(concertEvent.getCreatedBy()),
                concertEvent.getStyles(), simpleVenueDTO, name);

        return concertEventDTO;
    }

    public ConcertEvent mapNewConcertEventDTOToCOncertEvent(NewConcertEventDTO newConcertEventDTO) {
        ConcertEvent concertEvent = new ConcertEvent();

        concertEvent.setStartDateAndTime(newConcertEventDTO.startDateAndTime());
        concertEvent.setEndDateAndTime(newConcertEventDTO.endDateAndTime());
        concertEvent.setPerformers(getPerformers(newConcertEventDTO.performers()));
        concertEvent.setCreatedBy(getUser(newConcertEventDTO.createdBy()));
        concertEvent.setStyles(getStyles(newConcertEventDTO.styles()));
        concertEvent.setVenue(getVenue(newConcertEventDTO.venueId()));

        return concertEvent;
    }

    private Set<Performer> getPerformers(Set<String> performers) {
        Set<Performer> performerSet = new HashSet<>();

        performers.forEach(performer -> {
            Performer currentPerformer =
                    performerRepository.findByName(performer).orElse(performerRepository.save(new Performer(performer)));
            performerSet.add(currentPerformer);
        });

        return performerSet;
    }

    private Set<Style> getStyles(Set<String> styles) {
        Set<Style> styleSet = new HashSet<>();

        styles.forEach(style -> {
            Style currentStyle =
                    styleRepository.findByName(style).orElse(styleRepository.save(new Style(style)));
            styleSet.add(currentStyle);
        });

        return styleSet;
    }

    private User getUser(String id) {
        UUID idASUUID = UUID.fromString(id);
        User creator =
                userRepository.findById(idASUUID).orElseThrow(() -> new EntityNotFoundException(
                        "User with id: " + id + " not found"));
        return creator;
    }

    private Venue getVenue(String venueId) {
        UUID venueUUID = UUID.fromString(venueId);
        Venue venue =
                venueRepository.findById(venueUUID).orElseThrow(() -> new EntityNotFoundException("Venue wit id " + venueId + " not found!"));
        return venue;
    }
}
