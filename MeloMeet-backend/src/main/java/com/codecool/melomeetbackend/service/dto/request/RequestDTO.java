package com.codecool.melomeetbackend.service.dto.request;

import com.codecool.melomeetbackend.service.dto.user.UserDTO;

import java.sql.Timestamp;

public record RequestDTO(String requestId, String link, String msg, UserDTO sender,
                         UserDTO receiver, Timestamp dateOfCreation, boolean isRead) {
}
