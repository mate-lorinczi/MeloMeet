package com.codecool.melomeetbackend.controller;

import com.codecool.melomeetbackend.model.eventModel.ConcertEvent;
import com.codecool.melomeetbackend.service.ConcertEventService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(controllers = ConcertEventControllerTest.class)
@AutoConfigureMockMvc()
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ConcertEventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConcertEventService concertEventService;
    @Test
    public void getAllConcertEvents() throws Exception {

        List<ConcertEvent> concertEventList = List.of(new ConcertEvent());

        Mockito.when(concertEventService.findAll()).thenReturn(concertEventList);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/events/all"));

        response.andDo(print()).andExpect(MockMvcResultMatchers.status().isAccepted());
    }
}