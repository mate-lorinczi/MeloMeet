package com.codecool.melomeetbackend.service.group;

import com.codecool.melomeetbackend.service.dto.group.GroupDTO;
import com.codecool.melomeetbackend.service.dto.group.NewGroupDTO;

public interface GroupService {

    GroupDTO addNewGroupForAConcertEvent(NewGroupDTO newGroupDTO);
    GroupDTO getAllOpenGroups();
    GroupDTO getAllOpenGroupsByConcertEventId(String eventId);
    GroupDTO getAllInvitedGroupsByUserId(String userId);


}
