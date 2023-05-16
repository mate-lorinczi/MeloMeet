package com.codecool.melomeetbackend.service.group;

import com.codecool.melomeetbackend.dto.events.ConcertEventDTO;
import com.codecool.melomeetbackend.dto.group.GroupDTO;
import com.codecool.melomeetbackend.dto.group.NewGroupDTO;
import com.codecool.melomeetbackend.model.eventModel.ConcertEvent;
import org.springframework.stereotype.Service;

public interface GroupService {

    GroupDTO<ConcertEventDTO> addNewConcertGroup(NewGroupDTO newGroupDTO);
}
