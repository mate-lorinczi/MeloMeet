package com.codecool.melomeetbackend.service.dto.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class NewInvitationDTO extends NewFriendRequestDTO{

    @Getter
    String groupId;

    public NewInvitationDTO(String senderId, String receiverId, String msg, String groupId) {
        super(senderId, receiverId, msg);
        this.groupId = groupId;
    }
}
