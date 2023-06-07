package com.codecool.melomeetbackend.service.dto.group;

import com.codecool.melomeetbackend.service.dto.events.ConcertEventDTO;
import com.codecool.melomeetbackend.service.dto.user.UserDTO;

import java.util.Set;

public record GroupDTO(String groupId, UserDTO creator, Set<UserDTO> members,
                                            boolean isOpen,
                                            Set<UserDTO> invited, String concertEventId,
                       String groupName
                       ) {
}
