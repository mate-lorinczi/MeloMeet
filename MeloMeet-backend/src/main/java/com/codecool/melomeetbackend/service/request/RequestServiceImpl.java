package com.codecool.melomeetbackend.service.request;

import com.codecool.melomeetbackend.model.Request;
import com.codecool.melomeetbackend.model.User;
import com.codecool.melomeetbackend.repository.RequestRepository;
import com.codecool.melomeetbackend.repository.UserRepository;
import com.codecool.melomeetbackend.service.dto.request.NewFriendRequestDTO;
import com.codecool.melomeetbackend.service.dto.request.NewInvitationDTO;
import com.codecool.melomeetbackend.service.dto.request.RequestDTO;
import com.codecool.melomeetbackend.service.user.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequestService {
    private final UserService userService;
    private final RequestRepository requestRepository;

    @Override
    public RequestDTO generateNewFriendRequest(NewFriendRequestDTO newFriendRequestDTO) {

        Request request = new Request();

        User sender;
        User receiver;

        try {
            sender = userService.queryUserById(newFriendRequestDTO.senderId());
            receiver = userService.queryUserById(newFriendRequestDTO.receiverId());
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Error: " + e.getMessage(), e);
        }

        String link =
                "/users/" + newFriendRequestDTO.senderId() + "/" + newFriendRequestDTO.receiverId();
        request.setLink(link);
        request.setRead(false);
        request.setReceiver(receiver);
        request.setSender(sender);
        request.setMsg(newFriendRequestDTO.msg());

        Request savedRequest = requestRepository.save(request);

        return null;
    }

    @Override
    public RequestDTO generateNewGroupInviteRequest(NewInvitationDTO newInvitationDTO) {
        return null;
    }

    @Override
    public RequestDTO findRequestByRequestId(String requestId) {
        return null;
    }

    @Override
    public boolean deleteRequestByRequestId(String requestId) {
        return false;
    }
}
