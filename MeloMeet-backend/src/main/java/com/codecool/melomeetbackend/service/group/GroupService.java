package com.codecool.melomeetbackend.service.group;

import com.codecool.melomeetbackend.service.dto.group.GroupDTO;
import com.codecool.melomeetbackend.service.dto.group.NewGroupDTO;

import java.util.Set;

public interface GroupService {

    GroupDTO addNewGroupForAConcertEvent(NewGroupDTO newGroupDTO);
    Set<GroupDTO> getAllOpenGroups();
    Set<GroupDTO> getAllOpenGroupsByConcertEventId(String eventId);
    Set<GroupDTO> getAllInvitedGroupsByUserId(String userId);
    boolean inviteUserToGroup(String userId, String groupId);
    boolean acceptInvite(String userId, String groupId);

}
