package com.codecool.melomeetbackend.controller;

import com.codecool.melomeetbackend.dto.user.NewUserDTO;
import com.codecool.melomeetbackend.dto.user.UserDTO;
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

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
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
            UserDTO userDTO = userService.getUserByUserId(userId);

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
}
