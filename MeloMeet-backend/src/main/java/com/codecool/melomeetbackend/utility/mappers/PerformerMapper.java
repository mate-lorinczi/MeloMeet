package com.codecool.melomeetbackend.utility.mappers;

import com.codecool.melomeetbackend.dto.PerformerDTO;
import com.codecool.melomeetbackend.model.Performer;

public interface PerformerMapper {

    Performer perfromerDtoToPerformer(PerformerDTO performerDTO);
}
