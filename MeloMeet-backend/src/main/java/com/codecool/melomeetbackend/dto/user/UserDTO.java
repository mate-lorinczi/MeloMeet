package com.codecool.melomeetbackend.dto.user;

import java.sql.Timestamp;

public record UserDTO(String userId, String username, String email, Timestamp dateOfRegistration,
                      boolean isAdmin, boolean isBanned) {
}
