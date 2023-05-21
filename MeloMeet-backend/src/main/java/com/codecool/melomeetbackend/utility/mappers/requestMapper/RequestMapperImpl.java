package com.codecool.melomeetbackend.utility.mappers.requestMapper;

import com.codecool.melomeetbackend.model.Request;
import com.codecool.melomeetbackend.model.User;
import com.codecool.melomeetbackend.service.dto.request.RequestDTO;
import com.codecool.melomeetbackend.service.dto.user.UserDTO;
import com.codecool.melomeetbackend.service.user.UserService;
import com.codecool.melomeetbackend.utility.mappers.userMapper.UserMapper;

import java.sql.Timestamp;

public class RequestMapperImpl implements RequestMapper{

    private final UserMapper userMapper;

    public RequestMapperImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public RequestDTO mapRequestToRequestDTO(Request request) {
        UserDTO sender = userMapper.userEntityToUserDTO(request.getSender());
        UserDTO receiver = userMapper.userEntityToUserDTO(request.getReceiver());
        String requestId = request.getRequestId().toString();
        String link = request.getLink();
        String msg = request.getMsg();
        Timestamp dateOfCreation = request.getDateOfCreation();
        boolean isRead = request.isRead();

        return new RequestDTO(requestId, link, msg, sender, receiver, dateOfCreation, isRead);
    }
}
