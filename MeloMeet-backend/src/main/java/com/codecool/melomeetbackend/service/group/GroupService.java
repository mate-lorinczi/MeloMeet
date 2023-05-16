package com.codecool.melomeetbackend.service.group;

import com.codecool.melomeetbackend.dto.group.GroupDTO;
import com.codecool.melomeetbackend.dto.group.NewGroupDTO;

public interface GroupService {

    GroupDTO<ConcertEventDTOs> addNewGroupForAConcertEvent(NewGroupDTO newGroupDTO);
    GroupDTO<ConcertEventDTOs> getAllOpenGroups();
    GroupDTO<ConcertEventDTOs> getAllOpenGroupsByConcertEventId(String eventId);
    GroupDTO<EventDTOs> getAllInvitedGroupsByUserId(String userId);


}
