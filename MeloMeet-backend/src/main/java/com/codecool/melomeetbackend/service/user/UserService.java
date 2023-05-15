package com.codecool.melomeetbackend.service.user;

import com.codecool.melomeetbackend.dto.user.NewUserDTO;
import com.codecool.melomeetbackend.dto.user.UserDTO;

import java.util.Set;

public interface UserService {

    UserDTO addNewUser(NewUserDTO newUserDTO);
    UserDTO getUserByUserId(String userId);
    Set<UserDTO> getFriendsByUserId(String userId);
    UserDTO updateUser(UserDTO userDTO);
    boolean addFriend(String friendRequestSenderId, String friendRequestReceiverId);
    UserDTO getUserByUsername(String username);
}
