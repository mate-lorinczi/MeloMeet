package com.codecool.melomeetbackend.dto.group;

import com.codecool.melomeetbackend.dto.events.EventDTO;
import com.codecool.melomeetbackend.dto.user.UserDTO;
import com.codecool.melomeetbackend.model.eventModel.Event;

import java.util.Set;

public record GroupDTO<T extends EventDTO>(String groupId, UserDTO creator, Set<UserDTO> members,
                                    boolean isOpen,
                       Set<UserDTO> invited, T eventDTO) {
}
