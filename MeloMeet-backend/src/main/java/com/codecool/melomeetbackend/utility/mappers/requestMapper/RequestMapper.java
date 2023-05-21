package com.codecool.melomeetbackend.utility.mappers.requestMapper;

import com.codecool.melomeetbackend.model.Request;
import com.codecool.melomeetbackend.service.dto.request.RequestDTO;

public interface RequestMapper {

    RequestDTO mapRequestToRequestDTO(Request request);
}
