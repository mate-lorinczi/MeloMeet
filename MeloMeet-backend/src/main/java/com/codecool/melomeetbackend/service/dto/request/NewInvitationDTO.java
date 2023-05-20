package com.codecool.melomeetbackend.service.dto.request;

import lombok.*;
public class NewInvitationDTO extends NewRequestDTO{

    private final String groupId;

    public NewInvitationDTO(String senderId, String receiverId, String msg, String groupId) {
        super(senderId, receiverId, msg);
        this.groupId = groupId;
    }

    public String getGroupId() {
        return groupId;
    }
}
