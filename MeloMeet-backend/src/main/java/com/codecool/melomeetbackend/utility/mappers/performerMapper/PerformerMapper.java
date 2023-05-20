package com.codecool.melomeetbackend.utility.mappers.performerMapper;

import com.codecool.melomeetbackend.service.dto.PerformerDTO;
import com.codecool.melomeetbackend.model.Performer;

public interface PerformerMapper {

    Performer perfromerDtoToPerformer(PerformerDTO performerDTO);
}
