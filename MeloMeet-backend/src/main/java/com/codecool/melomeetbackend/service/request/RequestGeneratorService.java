package com.codecool.melomeetbackend.service.request;

import com.codecool.melomeetbackend.service.dto.request.NewFriendRequestDTO;
import com.codecool.melomeetbackend.service.dto.request.RequestDTO;

public interface RequestGeneratorService<T> {

     RequestDTO generateNewRequest(T newFriendRequestDTO);
}
