package com.codecool.melomeetbackend.controller;

import com.codecool.melomeetbackend.service.dto.group.GroupDTO;
import com.codecool.melomeetbackend.service.dto.user.LoginDTO;
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

        UserDTO addedUser = userService.addNewUser(newUserDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(addedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {

        String userId = userService.login(loginDTO);

        return ResponseEntity.status(HttpStatus.OK).body(userId);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserByUserId(@PathVariable String userId) {

        UserDTO userDTO = userService.getUserDTOByUserId(userId);

        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @GetMapping("/{userId}/friends")
    public ResponseEntity<?> getFriendsByUserId(@PathVariable String userId) {

        Set<UserDTO> friends = userService.getFriendsByUserId(userId);

        return ResponseEntity.status(HttpStatus.OK).body(friends);
    }

    /*
    TODO: Might move this method to group controller.
     */
    @GetMapping("/{userId}/invited")
    public ResponseEntity<?> getInvitedGroupsByUserId(@PathVariable String userId) {

        Set<GroupDTO> invitedGroups = groupService.getAllInvitedGroupsByUserId(userId);

        return ResponseEntity.status(HttpStatus.OK).body(invitedGroups);

    }


    @PatchMapping("")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO) {

        UserDTO updatedUser = userService.updateUser(userDTO);

        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @PatchMapping("{requestSenderId}/friends/{requestReceiverId}")
    public ResponseEntity<?> addFriend(@PathVariable String requestSenderId,
                                       @PathVariable String requestReceiverId) {
        boolean result = userService.addFriend(requestSenderId, requestReceiverId);

        if (!result) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server " +
                    "Error!");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Friend added!");
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getUsersBySearchStringOfName(@RequestParam String name) {

        Set<UserDTO> userDTOS = userService.getUsersBySearchString(name);

        return ResponseEntity.status(HttpStatus.OK).body(userDTOS);
    }

}
