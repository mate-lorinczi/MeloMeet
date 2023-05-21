package com.codecool.melomeetbackend.controller;

import com.codecool.melomeetbackend.service.dto.request.NewFriendRequestDTO;
import com.codecool.melomeetbackend.service.dto.request.NewInvitationDTO;
import com.codecool.melomeetbackend.service.dto.request.RequestDTO;
import com.codecool.melomeetbackend.service.request.RequestService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/requests")
public class RequestController {

    private final RequestService requestService;

    @PostMapping("/friend")
    public ResponseEntity<?> addNewFriendRequest(@RequestBody NewFriendRequestDTO newFriendRequestDTO) {

        try {
            RequestDTO requestDTO = requestService.generateNewFriendRequest(newFriendRequestDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(requestDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found!");
        }
    }

    @PostMapping("/group")
    public ResponseEntity<?> addNewGroupInviteRequest(@RequestBody NewInvitationDTO newInvitationDTO) {

        try {
            RequestDTO requestDTO = requestService.generateNewGroupInviteRequest(newInvitationDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(requestDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found!");
        }
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<?> getRequestByRequestId(@PathVariable String requestId) {

        try {
            RequestDTO requestDTO = requestService.findRequestByRequestId(requestId);

            return ResponseEntity.status(HttpStatus.OK).body(requestDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found!");
        }
    }
    
}
