package com.codecool.melomeetbackend.service.group;

import com.codecool.melomeetbackend.service.dto.group.GroupDTO;
import com.codecool.melomeetbackend.service.dto.group.NewGroupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService{

    private final GroupService groupService;

    @Autowired
    public GroupServiceImpl(GroupService groupService) {
        this.groupService = groupService;
    }

    @Override
    public GroupDTO addNewGroupForAConcertEvent(NewGroupDTO newGroupDTO) {
        return null;
    }

    @Override
    public GroupDTO getAllOpenGroups() {
        return null;
    }

    @Override
    public GroupDTO getAllOpenGroupsByConcertEventId(String eventId) {
        return null;
    }

    @Override
    public GroupDTO getAllInvitedGroupsByUserId(String userId) {
        return null;
    }
}
