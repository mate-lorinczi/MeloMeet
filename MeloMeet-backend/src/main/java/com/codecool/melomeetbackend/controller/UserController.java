package com.codecool.melomeetbackend.controller;

import com.codecool.melomeetbackend.service.dto.group.GroupDTO;
import com.codecool.melomeetbackend.service.dto.user.NewUserDTO;
import com.codecool.melomeetbackend.service.dto.user.UserDTO;
import com.codecool.melomeetbackend.service.group.GroupService;
import com.codecool.melomeetbackend.service.user.UserService;
import com.codecool.melomeetbackend.utility.excepiton.UserRegistrationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final GroupService groupService;

    @Autowired
    public UserController(UserService userService, GroupService groupService) {
        this.userService = userService;
        this.groupService = groupService;
    }

    @PostMapping("")
    public ResponseEntity<?> addNewUser(@RequestBody NewUserDTO newUserDTO) {

        try {
            UserDTO addedUser = userService.addNewUser(newUserDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(addedUser);
        } catch (UserRegistrationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Problem with the request!");
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserByUserId(@PathVariable String userId) {

        try {
            UserDTO userDTO = userService.getUserDTOByUserId(userId);

            return ResponseEntity.status(HttpStatus.OK).body(userDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Problem with the request!");
        }
    }

    @GetMapping("/{userId}/friends")
    public ResponseEntity<?> getFriendsByUserId(@PathVariable String userId) {

        try {
            Set<UserDTO> friends = userService.getFriendsByUserId(userId);

            return ResponseEntity.status(HttpStatus.OK).body(friends);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Problem with the request!");
        }
    }

    /*
    TODO: Might move this method to group controller.
     */
    @GetMapping("/{userId}/invited")
    public ResponseEntity<?> getInvitedGroupsByUserId(@PathVariable String userId) {
        try {
            Set<GroupDTO> invitedGroups = groupService.getAllInvitedGroupsByUserId(userId);

            return ResponseEntity.status(HttpStatus.OK).body(invitedGroups);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found!");
        }

    }


    @PatchMapping("")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO) {

        try {
            UserDTO updatedUser = userService.updateUser(userDTO);

            return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Problem with the request!");
        }
    }

    @PatchMapping("{requestSenderId}/friends/{requestReceiverId}")
    public ResponseEntity<?> addFriend(
            @PathVariable String requestSenderId,
            @PathVariable String requestReceiverId
                                       ) {
        try {
            boolean result = userService.addFriend(requestSenderId, requestReceiverId);

            if (!result) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server " +
                        "Error!");
            } else {
                return ResponseEntity.status(HttpStatus.OK).body("Friend added!");
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Problem with the request!");
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getUsersBySearchStringOfName(@RequestParam String name) {
        try {
            Set<UserDTO> userDTOS = userService.getUsersBySearchString(name);

            return ResponseEntity.status(HttpStatus.OK).body(userDTOS);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
