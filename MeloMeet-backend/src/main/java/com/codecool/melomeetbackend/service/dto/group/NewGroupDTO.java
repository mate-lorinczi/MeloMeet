package com.codecool.melomeetbackend.service.dto.group;

public record NewGroupDTO(String creatorId, boolean openToNonFriends, String eventId) {
}
