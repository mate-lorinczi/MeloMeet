package com.codecool.melomeetbackend.service.performer;

import com.codecool.melomeetbackend.service.dto.PerformerDTO;
import com.codecool.melomeetbackend.model.Performer;

import java.util.Set;

public interface PerformerService {

    Performer addNewPerformer(PerformerDTO performerDTO);
    Performer findPerformerById(String id);
    Performer findPerformerByName(String name);
    Performer deleteById(String id);
    Set<Performer> findPerformersByFractionOfName(String performerNameFraction);
    Set<Performer> findByEventId(String id);
}
