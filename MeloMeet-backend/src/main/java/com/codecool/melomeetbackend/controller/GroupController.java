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

    @PatchMapping("/{groupId}/invite/{userId}")
    public ResponseEntity<?> inviteUserToGroup(
            @PathVariable String groupId,
            @PathVariable String userId
    ) {
        try {
            boolean resultOfInvitation = groupService.inviteUserToGroup(userId, groupId);
            if (resultOfInvitation) {
                return ResponseEntity.status(HttpStatus.OK).body("Successful invitation!");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Problem with the " +
                        "request!");
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping("/{groupId}/accept/{userId}")
    public ResponseEntity<?> acceptInvitationToGroup(
            @PathVariable String groupId,
            @PathVariable String userId
    ) {
        try {
            boolean resultOfAccept = groupService.acceptInvite(userId, groupId);

            if(resultOfAccept) {
                return ResponseEntity.status(HttpStatus.OK).body("Invite accepted!");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Problem with the " +
                        "request!");
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Could not accept request!");
        }
    }
}
