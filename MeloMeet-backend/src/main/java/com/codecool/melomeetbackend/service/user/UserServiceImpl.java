package com.codecool.melomeetbackend.service.user;

import com.codecool.melomeetbackend.dto.user.NewUserDTO;
import com.codecool.melomeetbackend.dto.user.UserDTO;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.util.Set;

public class UserServiceImpl implements UserService {

    @Override
    public UserDTO addNewUser(NewUserDTO newUserDTO) {
        return null;
    }

    @Override
    public UserDTO getUserByUserId(String userId) {
        return null;
    }

    @Override
    public Set<UserDTO> getFriendsByUserId(String userId) {
        return null;
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public boolean addFriend(String friendRequestSenderId, String friendRequestReceiverId) {
        return false;
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        return null;
    }
}
