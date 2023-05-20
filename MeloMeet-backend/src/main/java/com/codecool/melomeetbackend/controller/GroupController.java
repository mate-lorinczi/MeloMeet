package com.codecool.melomeetbackend.controller;

import com.codecool.melomeetbackend.service.dto.group.GroupDTO;
import com.codecool.melomeetbackend.service.dto.group.NewGroupDTO;
import com.codecool.melomeetbackend.service.group.GroupService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    @PostMapping("")
    public ResponseEntity<?> addNewGroup(NewGroupDTO newGroupDTO) {

        try {
            GroupDTO addedGroup = groupService.addNewGroupForAConcertEvent(newGroupDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(addedGroup);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    
}
