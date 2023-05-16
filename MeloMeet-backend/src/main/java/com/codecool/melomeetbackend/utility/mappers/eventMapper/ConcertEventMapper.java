package com.codecool.melomeetbackend.utility.mappers.eventMapper;

import com.codecool.melomeetbackend.model.Performer;
import com.codecool.melomeetbackend.model.Style;
import com.codecool.melomeetbackend.model.User;
import com.codecool.melomeetbackend.model.eventModel.ConcertEvent;
import com.codecool.melomeetbackend.repository.PerformerRepository;
import com.codecool.melomeetbackend.repository.StyleRepository;
import com.codecool.melomeetbackend.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class ConcertEventMapper implements EventMapper<ConcertEvent, ConcertEventDTOs> {

    private final PerformerRepository performerRepository;
    private final StyleRepository styleRepository;
    private final UserRepository userRepository;

    @Autowired
    public ConcertEventMapper(PerformerRepository performerRepository,
                              StyleRepository styleRepository,
                              UserRepository userRepository
    ) {
        this.performerRepository = performerRepository;
        this.styleRepository = styleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ConcertEvent mapDTOToEvent(ConcertEventDTOs eventDTO) {
        LocalDateTime startDateAndTime = eventDTO.getStartDateAndTime();
        LocalDateTime endDateAndTime = eventDTO.getEndDateAndTime();
        Set<Performer> performers = getPerformers(eventDTO.getPerformers());
        Set<Style> styleSet = getStyles(eventDTO.getStyles());
        User creator = getUser(eventDTO.getCreatedBy());

        ConcertEvent concertEvent = new ConcertEvent();
        concertEvent.setStyles(styleSet);
        concertEvent.setPerformers(performers);
        concertEvent.setStartDateAndTime(startDateAndTime);
        concertEvent.setEndDateAndTime(endDateAndTime);

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
