package com.codecool.melomeetbackend.utility.mappers.eventMapper;

import com.codecool.melomeetbackend.model.Performer;
import com.codecool.melomeetbackend.model.Style;
import com.codecool.melomeetbackend.model.User;
import com.codecool.melomeetbackend.model.eventModel.ConcertEvent;
import com.codecool.melomeetbackend.repository.PerformerRepository;
import com.codecool.melomeetbackend.repository.StyleRepository;
import com.codecool.melomeetbackend.repository.UserRepository;
import com.codecool.melomeetbackend.service.dto.events.ConcertEventDTO;
import com.codecool.melomeetbackend.service.dto.user.UserDTO;
import com.codecool.melomeetbackend.utility.mappers.userMapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ConcertEventMapperTest {

    @Autowired
    private EventMapper eventMapper;
    @MockBean
    private PerformerRepository performerRepository;
    @MockBean
    private StyleRepository styleRepository;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private UserMapper userMapper;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        this.userDTO = new UserDTO("userId", "username", "email",
                Timestamp.valueOf(LocalDateTime.now()), false, false);
    }

    @Test
    void testEventMapperCorrectlyMapsEntityToDTO() {
        UUID id = UUID.randomUUID();
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusDays(1);
        Performer performer = new Performer();
        Set<Performer> performerSet = Set.of(performer);
        Style style = new Style();
        Set<Style> styleSet = Set.of(style);
        User user = User.builder().userID(UUID.randomUUID()).build();


        //when(userMapper.userEntityToUserDTO(any())).thenReturn(this.userDTO);

        ConcertEvent concertEvent =
                ConcertEvent.builder().eventId(id).startDateAndTime(startDate).endDateAndTime(endDate).performers(performerSet).styles(styleSet).createdBy(user).build();

        ConcertEventDTO eventDTO = eventMapper.mapConcertEventToConcertEventDTO(concertEvent);

        assertAll(
                () -> assertEquals(concertEvent.getEventId().toString(), eventDTO.eventId()),
                () -> assertTrue(concertEvent.getEndDateAndTime().equals(eventDTO.endDateAndTime()))
        );
    }

}