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
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequestService {
    private final UserService userService;
    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;

    @Override
    public RequestDTO generateNewFriendRequest(NewFriendRequestDTO newFriendRequestDTO) {

        Request request = getRequest(newFriendRequestDTO);

        String link =
                "/users/" + newFriendRequestDTO.getSenderId() + "/" + newFriendRequestDTO.getReceiverId();
        request.setLink(link);

        Request savedRequest = requestRepository.save(request);

        return requestMapper.mapRequestToRequestDTO(savedRequest);
    }

    @Override
    public RequestDTO generateNewGroupInviteRequest(NewInvitationDTO newInvitationDTO) {
        Request request = getRequest(newInvitationDTO);

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

    private Request generateRequest()

    @Override
    public RequestDTO findRequestByRequestId(String requestId) {
        return null;
    }

    @Override
    public boolean deleteRequestByRequestId(String requestId) {
        return false;
    }
}
