package com.codecool.melomeetbackend.service.user;

import com.codecool.melomeetbackend.dto.user.NewUserDTO;
import com.codecool.melomeetbackend.dto.user.UserDTO;
import com.codecool.melomeetbackend.model.User;
import com.codecool.melomeetbackend.repository.UserRepository;
import com.codecool.melomeetbackend.utility.excepiton.UserRegistrationException;
import com.codecool.melomeetbackend.utility.mappers.userMapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO addNewUser(NewUserDTO newUserDTO) throws UserRegistrationException {

        UserDTO userDTO;

        try {
            User newUser = userMapper.newUserDTOToUser(newUserDTO);
            User savedUser = userRepository.save(newUser);

            userDTO = userMapper.userEntityToUserDTO(savedUser);

        } catch (UserRegistrationException e) {
            throw e;
        }

        return userDTO;
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
