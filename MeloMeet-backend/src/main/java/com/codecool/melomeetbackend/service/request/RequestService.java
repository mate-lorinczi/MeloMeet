package com.codecool.melomeetbackend.service.request;

import com.codecool.melomeetbackend.service.dto.request.NewFriendRequestDTO;
import com.codecool.melomeetbackend.service.dto.request.NewInvitationDTO;
import com.codecool.melomeetbackend.service.dto.request.RequestDTO;

public interface RequestService {

    RequestDTO generateNewFriendRequest(NewFriendRequestDTO newFriendRequestDTO);
    RequestDTO generateNewGroupInviteRequest(NewInvitationDTO newInvitationDTO);
    RequestDTO findRequestByRequestId(String requestId);
    boolean deleteRequestByRequestId(String requestId);
}
