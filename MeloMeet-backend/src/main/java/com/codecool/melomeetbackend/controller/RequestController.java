package com.codecool.melomeetbackend.controller;

import com.codecool.melomeetbackend.service.dto.request.NewFriendRequestDTO;
import com.codecool.melomeetbackend.service.dto.request.NewInvitationDTO;
import com.codecool.melomeetbackend.service.dto.request.RequestDTO;
import com.codecool.melomeetbackend.service.request.RequestService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/requests")
public class RequestController {

    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping("/friend")
    public ResponseEntity<?> addNewFriendRequest(@RequestBody NewFriendRequestDTO newFriendRequestDTO) {

        RequestDTO requestDTO = requestService.generateNewFriendRequest(newFriendRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(requestDTO);
    }

    @PostMapping("/group")
    public ResponseEntity<?> addNewGroupInviteRequest(@RequestBody NewInvitationDTO newInvitationDTO) {

        RequestDTO requestDTO = requestService.generateNewGroupInviteRequest(newInvitationDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(requestDTO);

    }

    @GetMapping("/{requestId}")
    public ResponseEntity<?> getRequestByRequestId(@PathVariable String requestId) {

        RequestDTO requestDTO = requestService.findRequestByRequestId(requestId);

        return ResponseEntity.status(HttpStatus.OK).body(requestDTO);
    }

}
