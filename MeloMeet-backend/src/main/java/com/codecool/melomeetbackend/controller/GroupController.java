package com.codecool.melomeetbackend.controller;

import com.codecool.melomeetbackend.service.dto.group.GroupDTO;
import com.codecool.melomeetbackend.service.dto.group.NewGroupDTO;
import com.codecool.melomeetbackend.service.group.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("")
    public ResponseEntity<?> addNewGroup(@RequestBody NewGroupDTO newGroupDTO) {
        GroupDTO addedGroup = groupService.addNewGroupForAConcertEvent(newGroupDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(addedGroup);
    }

    @GetMapping("/open")
    public ResponseEntity<?> getOpenGroups(@RequestParam(defaultValue = "") String eventId) {

        if (eventId.equals("")) {
            Set<GroupDTO> openGroups = groupService.getAllOpenGroups();

            return ResponseEntity.status(HttpStatus.OK).body(openGroups);
        } else {
            Set<GroupDTO> openGroupsByEventId =
                    groupService.getAllOpenGroupsByConcertEventId(eventId);

            return ResponseEntity.status(HttpStatus.OK).body(openGroupsByEventId);
        }
    }

    @PatchMapping("/{groupId}/invite/{userId}")
    public ResponseEntity<?> inviteUserToGroup(@PathVariable String groupId,
                                               @PathVariable String userId) {
        boolean resultOfInvitation = groupService.inviteUserToGroup(userId, groupId);
        if (resultOfInvitation) {
            return ResponseEntity.status(HttpStatus.OK).body("Successful invitation!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Problem with the " +
                    "request!");
        }
    }

    @PatchMapping("/{groupId}/accept/{userId}")
    public ResponseEntity<?> acceptInvitationToGroup(@PathVariable String groupId,
                                                     @PathVariable String userId) {
        boolean resultOfAccept = groupService.acceptInvite(userId, groupId);

        if (resultOfAccept) {
            return ResponseEntity.status(HttpStatus.OK).body("Invite accepted!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Problem with the " +
                    "request!");
        }
    }
}
