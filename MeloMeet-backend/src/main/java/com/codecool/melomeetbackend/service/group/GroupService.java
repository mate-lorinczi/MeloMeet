package com.codecool.melomeetbackend.service.group;

import com.codecool.melomeetbackend.dto.events.ConcertEventDTO;
import com.codecool.melomeetbackend.dto.events.EventDTO;
import com.codecool.melomeetbackend.dto.group.GroupDTO;
import com.codecool.melomeetbackend.dto.group.NewGroupDTO;
import com.codecool.melomeetbackend.model.eventModel.ConcertEvent;
import org.springframework.stereotype.Service;

public interface GroupService {

    GroupDTO<ConcertEventDTO> addNewGroupForAConcertEvent(NewGroupDTO newGroupDTO);
    GroupDTO<ConcertEventDTO> getAllOpenGroups();
    GroupDTO<ConcertEventDTO> getAllOpenGroupsByConcertEventId(String eventId);
    GroupDTO<EventDTO> getAllInvitedGroupsByUserId(String userId);
    

}
