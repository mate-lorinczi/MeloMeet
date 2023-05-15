package com.codecool.melomeetbackend.utility.mappers.performerMapper;

import com.codecool.melomeetbackend.dto.PerformerDTO;
import com.codecool.melomeetbackend.model.Performer;
import com.codecool.melomeetbackend.utility.mappers.performerMapper.PerformerMapper;
import org.springframework.stereotype.Service;

@Service
public class PerformerMapperImpl implements PerformerMapper {

    @Override
    public Performer perfromerDtoToPerformer(PerformerDTO performerDTO) {
        Performer performer = new Performer();

        performer.setName(performerDTO.name());

        return performer;
    }
}