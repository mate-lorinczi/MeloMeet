package com.codecool.melomeetbackend.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

public class NewRequestDTO {

    public final String senderId;
    public final String receiverId;
    public final String msg;

    public NewRequestDTO(String senderId, String receiverId, String msg) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.msg = msg;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public String getMsg() {
        return msg;
    }
}
