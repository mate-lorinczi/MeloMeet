package com.codecool.melomeetbackend.controller;

import com.codecool.melomeetbackend.service.dto.group.GroupDTO;
import com.codecool.melomeetbackend.service.dto.group.NewGroupDTO;
import com.codecool.melomeetbackend.service.group.GroupService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Set;

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

    @GetMapping("/open")
    public ResponseEntity<?> getOpenGroups(@RequestParam(defaultValue = "") String eventId) {

        try {
            if (eventId.equals("")) {
                Set<GroupDTO> openGroups = groupService.getAllOpenGroups();

                return ResponseEntity.status(HttpStatus.OK).body(openGroups);
            } else {
                Set<GroupDTO> openGroupsByEventId =
                        groupService.getAllOpenGroupsByConcertEventId(eventId);

                return ResponseEntity.status(HttpStatus.OK).body(openGroupsByEventId);
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found!");
        }

    }
}
