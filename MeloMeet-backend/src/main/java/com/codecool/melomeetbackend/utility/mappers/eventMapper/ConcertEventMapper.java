package com.codecool.melomeetbackend.utility.mappers.eventMapper;

import com.codecool.melomeetbackend.service.dto.events.ConcertEventDTO;
import com.codecool.melomeetbackend.service.dto.events.NewConcertEventDTO;
import com.codecool.melomeetbackend.model.Performer;
import com.codecool.melomeetbackend.model.Style;
import com.codecool.melomeetbackend.model.User;
import com.codecool.melomeetbackend.model.eventModel.ConcertEvent;
import com.codecool.melomeetbackend.repository.PerformerRepository;
import com.codecool.melomeetbackend.repository.StyleRepository;
import com.codecool.melomeetbackend.repository.UserRepository;
import com.codecool.melomeetbackend.utility.mappers.userMapper.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class ConcertEventMapper implements EventMapper {

    private final PerformerRepository performerRepository;
    private final StyleRepository styleRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public ConcertEventMapper(PerformerRepository performerRepository,
                              StyleRepository styleRepository,
                              UserRepository userRepository,
                              UserMapper userMapper
    ) {
        this.performerRepository = performerRepository;
        this.styleRepository = styleRepository;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
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
        ConcertEventDTO concertEventDTO = new ConcertEventDTO(eventId.toString(),
                concertEvent.getStartDateAndTime(), concertEvent.getEndDateAndTime(),
                concertEvent.getPerformers(),
                userMapper.userEntityToUserDTO(concertEvent.getCreatedBy()),
                concertEvent.getStyles());

        return concertEventDTO;
    }

    //TODO: Add venue mapping to mapper!
    public ConcertEvent mapNewConcertEventDTOToCOncertEvent(NewConcertEventDTO newConcertEventDTO) {
        ConcertEvent concertEvent = new ConcertEvent();

        concertEvent.setStartDateAndTime(newConcertEventDTO.startDateAndTime());
        concertEvent.setEndDateAndTime(newConcertEventDTO.endDateAndTime());
        concertEvent.setPerformers(getPerformers(newConcertEventDTO.performers()));
        concertEvent.setCreatedBy(getUser(newConcertEventDTO.createdBy()));
        concertEvent.setStyles(getStyles(newConcertEventDTO.styles()));

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
}
