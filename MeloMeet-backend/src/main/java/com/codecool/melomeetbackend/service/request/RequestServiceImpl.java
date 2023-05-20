package com.codecool.melomeetbackend.service.request;

import com.codecool.melomeetbackend.service.dto.request.NewRequestDTO;
import com.codecool.melomeetbackend.service.dto.request.RequestDTO;

public class RequestServiceImpl implements RequestService{


    @Override
    public <T extends NewRequestDTO> RequestDTO generateNewRequest(Class<T> newFriendRequestDTO) {
        return null;
    }
}
