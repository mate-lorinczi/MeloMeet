package com.codecool.melomeetbackend.service.request;

import com.codecool.melomeetbackend.service.dto.request.NewFriendRequestDTO;
import com.codecool.melomeetbackend.service.dto.request.NewRequestDTO;
import com.codecool.melomeetbackend.service.dto.request.RequestDTO;

public interface RequestService {

    <T extends NewRequestDTO> RequestDTO generateNewRequest(Class<T> newFriendRequestDTO);
}
