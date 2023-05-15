package com.codecool.melomeetbackend.utility.mappers.userMapper;

import com.codecool.melomeetbackend.dto.user.NewUserDTO;
import com.codecool.melomeetbackend.dto.user.UserDTO;
import com.codecool.melomeetbackend.model.User;
import com.codecool.melomeetbackend.utility.excepiton.UserRegistrationException;

public interface UserMapper {

    User newUserDTOToUser(NewUserDTO newUserDTO) throws UserRegistrationException;
    UserDTO userEntityToUserDTO(User user);
}
