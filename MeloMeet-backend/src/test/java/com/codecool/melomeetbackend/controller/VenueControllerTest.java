package com.codecool.melomeetbackend.controller;

import com.codecool.melomeetbackend.service.dto.venue.NewVenueDTO;
import com.codecool.melomeetbackend.service.dto.venue.VenueDTO;
import com.codecool.melomeetbackend.service.dto.venue.WholeAddress;
import com.codecool.melomeetbackend.service.venue.VenueService;
import com.codecool.melomeetbackend.service.venue.VenueServiceImp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc()
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class VenueControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean(classes = VenueServiceImp.class)
    private VenueService venueService;

    private NewVenueDTO newVenueDTO;
    private VenueDTO venueDTO;

    @BeforeEach
    void setUp() {

        String name = "Test venue";
        WholeAddress wholeAddress = new WholeAddress("Budapest", "1123", "Alkotás utca 23");
        String uuid = UUID.randomUUID().toString();

        this.newVenueDTO = new NewVenueDTO(name, wholeAddress, true);
        this.venueDTO = new VenueDTO(name, wholeAddress, true, uuid);
    }

    @Test
    void testAddNewVenueSuccessfully() throws Exception {

        String json = objectMapper.writeValueAsString(newVenueDTO);

        String responseJson = objectMapper.writeValueAsString(venueDTO);

        Mockito.when(venueService.addNewVenue(newVenueDTO)).thenReturn(venueDTO);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/venues")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON));

        response.andDo(print()).andExpectAll(MockMvcResultMatchers.status().isCreated(),
                MockMvcResultMatchers.content().json(responseJson));
    }

    @Test
    void testAddNewVenueWithAlreadyUsedAddress() throws Exception {

        Mockito.when(venueService.addNewVenue(Mockito.any())).thenThrow(new EntityExistsException("Venue with the same address already exists!"));
        String json = objectMapper.writeValueAsString(newVenueDTO);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/venues").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON));

        resultActions.andDo(print()).andExpectAll(MockMvcResultMatchers.status().isConflict(),
                MockMvcResultMatchers.content().string("Venue with the same address already exists!"));
    }
}