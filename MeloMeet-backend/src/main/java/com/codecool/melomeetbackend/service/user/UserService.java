package com.codecool.melomeetbackend.service.user;

import com.codecool.melomeetbackend.model.User;
import com.codecool.melomeetbackend.service.dto.user.NewUserDTO;
import com.codecool.melomeetbackend.service.dto.user.UserDTO;
import com.codecool.melomeetbackend.utility.excepiton.UserRegistrationException;

import java.util.Set;

public interface UserService {

    UserDTO addNewUser(NewUserDTO newUserDTO) throws UserRegistrationException;
    UserDTO getUserDTOByUserId(String userId);
    Set<UserDTO> getFriendsByUserId(String userId);
    UserDTO updateUser(UserDTO userDTO);
    boolean addFriend(String friendRequestSenderId, String friendRequestReceiverId);
    UserDTO getUserByUsername(String username);
    Set<UserDTO> getUsersBySearchString(String searchString);
    User queryUserById(String id);
}
