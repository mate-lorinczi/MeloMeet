package com.codecool.melomeetbackend.service.dto.request;

import lombok.Getter;
import lombok.Value;

public class NewFriendRequestDTO {

    @Getter
    private final String senderId;
    @Getter
    private final String receiverId;
    @Getter
    private final String msg;

    public NewFriendRequestDTO(String senderId, String receiverId, String msg) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.msg = msg;
    }
}
