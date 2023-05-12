package com.codecool.melomeetbackend.service;

import com.codecool.melomeetbackend.dto.PerformerDTO;
import com.codecool.melomeetbackend.model.Performer;

public interface PerformerRepository {

    Performer addNewPerformer(PerformerDTO performerDTO);
    Performer findPerformerById(String id);
    Performer findPerformerByName(String name);

}
