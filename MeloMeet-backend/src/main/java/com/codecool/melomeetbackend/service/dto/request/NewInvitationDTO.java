package com.codecool.melomeetbackend.service.dto.request;

public record NewInvitationDTO(String senderId, String receiverId, String groupId, String msg) {
}
