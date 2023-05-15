package com.codecool.melomeetbackend.service.user;

import com.codecool.melomeetbackend.dto.user.NewUserDTO;

public interface UserService {

    UserDTO addNewUser(NewUserDTO newUserDTO);
}
