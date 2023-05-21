package com.codecool.melomeetbackend.service.dto.request;

public record NewFriendRequestDTO(String senderId, String receiverId, String msg) {
}
