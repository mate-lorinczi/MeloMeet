package com.codecool.melomeetbackend.service.group;

import com.codecool.melomeetbackend.model.Group;
import com.codecool.melomeetbackend.repository.GroupRepository;
import com.codecool.melomeetbackend.service.dto.group.GroupDTO;
import com.codecool.melomeetbackend.service.dto.group.NewGroupDTO;
import com.codecool.melomeetbackend.utility.mappers.groupMapper.GroupMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService{

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    @Autowired
    public GroupServiceImpl(
            GroupRepository groupRepository,
            GroupMapper groupMapper
            ) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
    }

    @Override
    public GroupDTO addNewGroupForAConcertEvent(NewGroupDTO newGroupDTO) {

        try {
            Group newGroup = groupMapper.mapNewGroupDTOToGroup(newGroupDTO);

            Group savedGroup = groupRepository.save(newGroup);

            return groupMapper.mapGroupToGroupDTO(savedGroup);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Error: " + e.getMessage(), e);
        }

    }

    @Override
    public Set<GroupDTO> getAllOpenGroups() {

        var openGroups =
                groupRepository.findAllByOpenToNonInvitedIsTrue().stream().map(groupMapper::mapGroupToGroupDTO).collect(Collectors.toSet());

        return openGroups;
    }

    @Override
    public GroupDTO getAllOpenGroupsByConcertEventId(String eventId) {
        return null;
    }

    @Override
    public GroupDTO getAllInvitedGroupsByUserId(String userId) {
        return null;
    }

    @Override
    public boolean inviteUserToGroup(String userId, String groupId) {
        return false;
    }

    @Override
    public boolean acceptInvite(String userId, String groupId) {
        return false;
    }
}
