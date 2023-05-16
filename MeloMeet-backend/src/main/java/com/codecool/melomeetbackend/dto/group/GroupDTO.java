package com.codecool.melomeetbackend.dto.group;

import com.codecool.melomeetbackend.dto.user.UserDTO;

import java.util.Set;

public record GroupDTO<T extends EventDTOs>(String groupId, UserDTO creator, Set<UserDTO> members,
                                            boolean isOpen,
                                            Set<UserDTO> invited, T eventDTO) {
}
