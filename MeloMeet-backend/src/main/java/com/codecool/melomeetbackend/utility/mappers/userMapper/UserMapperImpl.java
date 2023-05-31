package com.codecool.melomeetbackend.utility.mappers.userMapper;

import com.codecool.melomeetbackend.service.dto.user.NewUserDTO;
import com.codecool.melomeetbackend.service.dto.user.UserDTO;
import com.codecool.melomeetbackend.model.User;
import com.codecool.melomeetbackend.repository.UserRepository;
import com.codecool.melomeetbackend.utility.excepiton.UserRegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserMapperImpl implements UserMapper{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserMapperImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User newUserDTOToUser(NewUserDTO newUserDTO) throws UserRegistrationException {

        String username = newUserDTO.username();
        String email = newUserDTO.email();

        if(userRepository.existsByUsername(username)) {
            throw new UserRegistrationException("Username already in use");
        } else if (userRepository.existsByEmail(email)) {
            throw new UserRegistrationException("Email already in use");
        }

        User newUser = new User();

        newUser.setAdmin(false);
        newUser.setBanned(false);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(newUserDTO.password()));
        newUser.setUsername(username);

        return newUser;
    }

    @Override
    public UserDTO userEntityToUserDTO(User user) {
       UserDTO userDTO = new UserDTO(user.getUserID().toString(), user.getUsername(), user.getEmail(),
               user.getDateOfRegistration(), user.isAdmin(), user.isBanned());

       return userDTO;
    }
}
