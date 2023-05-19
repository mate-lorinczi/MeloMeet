package com.codecool.melomeetbackend.controller;

import com.codecool.melomeetbackend.model.Performer;
import com.codecool.melomeetbackend.model.Style;
import com.codecool.melomeetbackend.model.eventModel.ConcertEvent;
import com.codecool.melomeetbackend.service.dto.events.ConcertEventDTO;
import com.codecool.melomeetbackend.service.dto.user.UserDTO;
import com.codecool.melomeetbackend.service.event.ConcertEventServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

//@WebMvcTest(controllers = ConcertEventControllerTest.class)
@AutoConfigureMockMvc()
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ConcertEventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConcertEventServiceImpl concertEventServiceImpl;
    @Test
    public void getAllConcertEvents() throws Exception {

        Set<Performer> performers = new HashSet<>();
        UserDTO userDTO = new UserDTO("testUserId", "testUsername", "testEmail",
                Timestamp.valueOf(LocalDateTime.now()), false, false);
        Set<Style> styles = new HashSet<>();

        List<ConcertEventDTO> concertEventList = List.of(new ConcertEventDTO("testId",
                LocalDateTime.now(), LocalDateTime.now(),  performers, userDTO, styles));

        Mockito.when(concertEventServiceImpl.findAll()).thenReturn(concertEventList);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/events/all"));

        response.andDo(print()).andExpect(MockMvcResultMatchers.status().isAccepted());
    }
}