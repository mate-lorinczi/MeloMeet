package com.codecool.melomeetbackend.service.user;

import com.codecool.melomeetbackend.service.dto.user.LoginDTO;
import com.codecool.melomeetbackend.service.dto.user.NewUserDTO;
import com.codecool.melomeetbackend.service.dto.user.UserDTO;
import com.codecool.melomeetbackend.model.User;
import com.codecool.melomeetbackend.repository.UserRepository;
import com.codecool.melomeetbackend.utility.excepiton.UserRegistrationException;
import com.codecool.melomeetbackend.utility.mappers.userMapper.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
    public String login(LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.username())
                .orElseThrow(() -> new BadCredentialsException("Username or " +
                "password is wrong!"));
        if(!passwordEncoder.matches(loginDTO.password(), user.getPassword())) {
            throw new BadCredentialsException("Username or " +
                    "password is wrong!");
        }
        return user.getUserID().toString();
    }

    @Override
    public UserDTO getUserDTOByUserId(String userId) {
        User user;
        try {
            user = queryUserById(userId);
        } catch (EntityNotFoundException e) {
            throw e;
        }


        UserDTO userDTO = userMapper.userEntityToUserDTO(user);

        return userDTO;
    }

    public User queryUserById(String userId) {
        UUID userUUID = UUID.fromString(userId);
        User user =
                userRepository.findById(userUUID).orElseThrow(() -> new EntityNotFoundException(
                        "User with id " + userId + " not found!"));
        return user;
    }

    @Override
    public Set<UserDTO> getFriendsByUserId(String userId) {
        User user;
        try {
            user = queryUserById(userId);

        } catch (EntityNotFoundException e) {
            throw e;
        }

        Set<UserDTO> friends =
                user.getFriends().stream().map(userMapper::userEntityToUserDTO).collect(Collectors.toSet());

        return friends;
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        User user =
                userRepository.getReferenceById(UUID.fromString(userDTO.userId()));

        user.setUsername(userDTO.username());
        user.setEmail(userDTO.email());

        User updatedUser = userRepository.save(user);

        UserDTO updatedUserDTO = userMapper.userEntityToUserDTO(updatedUser);

        return updatedUserDTO;
    }

    /*
    TODO: Integrate with request service.
     */

    @Override
    public boolean addFriend(String friendRequestSenderId, String friendRequestReceiverId) {
        User requestSender;
        User requestReceiver;

        try {
            requestSender = queryUserById(friendRequestSenderId);
            requestReceiver = queryUserById(friendRequestReceiverId);
        } catch (EntityNotFoundException e) {
            throw e;
        }
        try {
            requestReceiver.getFriends().add(requestSender);
            requestSender.getFriends().add(requestReceiver);

            userRepository.save(requestReceiver);
            userRepository.save(requestSender);

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public UserDTO getUserByUsername(String username) {
        User user =
                userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User with username " + username + " not found!"));

        UserDTO userDTO = userMapper.userEntityToUserDTO(user);

        return userDTO;
    }

    @Override
    public Set<UserDTO> getUsersBySearchString(String searchString) {

         Set<User> userSet = userRepository.findAllByUsernameContaining(searchString);

         if(userSet.size() < 1) {
             throw new EntityNotFoundException("No user found with name containing string: " + searchString);
         }

         return userSet.stream().map(userMapper::userEntityToUserDTO).collect(Collectors.toSet());
    }
}
