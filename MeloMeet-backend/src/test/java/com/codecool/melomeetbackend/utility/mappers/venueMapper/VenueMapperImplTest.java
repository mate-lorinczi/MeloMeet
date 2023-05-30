package com.codecool.melomeetbackend.utility.mappers.venueMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class VenueMapperImplTest {

    @Autowired
    private VenueMapper venueMapper;

    @Test
    void testNewVenueDTOToVenue() {

    }
}