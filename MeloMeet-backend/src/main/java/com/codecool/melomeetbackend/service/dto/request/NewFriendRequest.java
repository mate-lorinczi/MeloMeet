package com.codecool.melomeetbackend.service.dto.request;

public record NewFriendRequest(String senderId, String receiverId, String msg) {
}
