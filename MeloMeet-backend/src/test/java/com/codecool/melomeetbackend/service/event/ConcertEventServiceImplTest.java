package com.codecool.melomeetbackend.service.event;

import com.codecool.melomeetbackend.MeloMeetBackendApplication;
import com.codecool.melomeetbackend.model.Performer;
import com.codecool.melomeetbackend.model.Style;
import com.codecool.melomeetbackend.model.User;
import com.codecool.melomeetbackend.model.Venue;
import com.codecool.melomeetbackend.model.eventModel.ConcertEvent;
import com.codecool.melomeetbackend.repository.ConcertEventRepository;
import com.codecool.melomeetbackend.repository.PerformerRepository;
import com.codecool.melomeetbackend.service.dto.events.ConcertEventDTO;
import com.codecool.melomeetbackend.service.dto.events.NewConcertEventDTO;
import com.codecool.melomeetbackend.service.dto.user.UserDTO;
import com.codecool.melomeetbackend.utility.mappers.eventMapper.ConcertEventMapper;
import com.codecool.melomeetbackend.utility.mappers.eventMapper.EventMapper;
import jakarta.persistence.EntityNotFoundException;
import org.apache.catalina.core.ApplicationContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ConcertEventServiceImplTest {

    @Autowired
    private ConcertEventService concertEventService;

    @MockBean
    private ConcertEventRepository concertEventRepository;
    @MockBean(classes = ConcertEventMapper.class)
    private EventMapper eventMapper;
    @MockBean
    private PerformerRepository performerRepository;
    private final UUID uuid = UUID.fromString("aa9336e0-303c-49ca-b97e-72fb3c0cdd21");
    private UserDTO userDTO;
    private Optional<ConcertEvent> concertEvent;
    private ConcertEventDTO concertEventDTO;

    @BeforeEach
    void setUp() {
        concertEventRepository.deleteAll();
        //UUID uuid = UUID.fromString("a7626f6a-f8c0-11ed-be56-0242ac120002");
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.plusHours(2);
        Performer performer = new Performer();
        Set<Performer> performerSet = Set.of(performer);
        Venue venue = new Venue();
        User user = new User();
        Style style = new Style();
        Set<Style> styleSet = Set.of(style);

         this.userDTO = new UserDTO("userId", "username", "email",
                Timestamp.valueOf(LocalDateTime.now()), false, false);

        this.concertEvent = Optional.of(new ConcertEvent(this.uuid, startTime,
                endTime, performerSet, venue, user, styleSet));
        this.concertEventDTO = new ConcertEventDTO(uuid.toString(), startTime, endTime
                , performerSet, userDTO, styleSet);
    }

    @Test
    public void testGetConcertEvenDTOtByIdSuccessfully() {
        Mockito.when(concertEventRepository.findById(this.uuid)).thenReturn(this.concertEvent);
        Mockito.when(eventMapper.mapConcertEventToConcertEventDTO(Mockito.any())).thenReturn(concertEventDTO);

        ConcertEventDTO concertEventDTO =
                concertEventService.getConcertEvenDTOtById(this.uuid.toString());
        assertEquals(uuid.toString(), concertEventDTO.eventId());
        Mockito.verify(concertEventRepository, Mockito.times(1)).findById(Mockito.any());
        Mockito.verify(eventMapper, Mockito.times(1)).mapConcertEventToConcertEventDTO(Mockito.any());
    }

    @Test
    public void testGetConcertEvenDTOtByIdWithNonExistentId() {
        String uuid1 = UUID.randomUUID().toString();
        String expectedErrorMessage = "Event with id: " + uuid1 + " not found";
        EntityNotFoundException entityNotFoundException = assertThrows(EntityNotFoundException.class, () -> concertEventService.getConcertEvenDTOtById(uuid1));
        Mockito.verify(concertEventRepository, Mockito.times(1)).findById(Mockito.any());
        assertEquals(expectedErrorMessage, entityNotFoundException.getMessage());
    }
}