package com.codecool.melomeetbackend.service.request;

import com.codecool.melomeetbackend.model.Request;
import com.codecool.melomeetbackend.model.User;
import com.codecool.melomeetbackend.repository.RequestRepository;
import com.codecool.melomeetbackend.repository.UserRepository;
import com.codecool.melomeetbackend.service.dto.request.NewFriendRequestDTO;
import com.codecool.melomeetbackend.service.dto.request.NewInvitationDTO;
import com.codecool.melomeetbackend.service.dto.request.RequestDTO;
import com.codecool.melomeetbackend.service.user.UserService;
import com.codecool.melomeetbackend.utility.mappers.requestMapper.RequestMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RequestServiceImpl implements RequestService {
    private final UserService userService;
    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;

    @Autowired
    public RequestServiceImpl(UserService userService, RequestRepository requestRepository, RequestMapper requestMapper) {
        this.userService = userService;
        this.requestRepository = requestRepository;
        this.requestMapper = requestMapper;
    }

    @Override
    public RequestDTO generateNewFriendRequest(NewFriendRequestDTO newFriendRequestDTO) {

        Request request;

        try {
             request = getRequest(newFriendRequestDTO);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(e.getMessage(), e);
        }


        String link =
                "/users/" + newFriendRequestDTO.getSenderId() + "/" + newFriendRequestDTO.getReceiverId();
        request.setLink(link);

        Request savedRequest = requestRepository.save(request);

        return requestMapper.mapRequestToRequestDTO(savedRequest);
    }

    @Override
    public RequestDTO generateNewGroupInviteRequest(NewInvitationDTO newInvitationDTO) {

        Request request;

        try {
            request = getRequest(newInvitationDTO);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(e.getMessage(), e);
        }



        String link =
                "/groups/" + newInvitationDTO.getGroupId() + "/accept/" + newInvitationDTO.getReceiverId();
        request.setLink(link);

        Request savedRequest = requestRepository.save(request);

        return requestMapper.mapRequestToRequestDTO(savedRequest);
    }

    private <T extends NewFriendRequestDTO> Request getRequest(T newRequestDTO) {
        Request request = new Request();

        User sender;
        User receiver;

        try {
            sender = userService.queryUserById(newRequestDTO.getSenderId());
            receiver = userService.queryUserById(newRequestDTO.getReceiverId());
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Error: " + e.getMessage(), e);
        }

        String msg = newRequestDTO.getMsg();

        request.setRead(false);
        request.setReceiver(receiver);
        request.setSender(sender);
        request.setMsg(msg);
        return request;
    }

    @Override
    public RequestDTO findRequestByRequestId(String requestId) {
        Request request =
                requestRepository.findById(UUID.fromString(requestId)).orElseThrow(() -> new EntityNotFoundException("Request with id: " + requestId + " not found!"));

        return requestMapper.mapRequestToRequestDTO(request);
    }

    @Override
    public boolean deleteRequestByRequestId(String requestId) {

        requestRepository.deleteById(UUID.fromString(requestId));
        return true;
    }
}
