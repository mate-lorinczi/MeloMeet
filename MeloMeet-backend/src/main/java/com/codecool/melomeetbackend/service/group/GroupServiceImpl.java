package com.codecool.melomeetbackend.service.group;

import com.codecool.melomeetbackend.model.Group;
import com.codecool.melomeetbackend.model.User;
import com.codecool.melomeetbackend.model.eventModel.ConcertEvent;
import com.codecool.melomeetbackend.repository.ConcertEventRepository;
import com.codecool.melomeetbackend.repository.GroupRepository;
import com.codecool.melomeetbackend.repository.UserRepository;
import com.codecool.melomeetbackend.service.dto.group.GroupDTO;
import com.codecool.melomeetbackend.service.dto.group.NewGroupDTO;
import com.codecool.melomeetbackend.utility.mappers.groupMapper.GroupMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService{

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final ConcertEventRepository concertEventRepository;
    private final UserRepository userRepository;

    @Autowired
    public GroupServiceImpl(
            GroupRepository groupRepository,
            GroupMapper groupMapper,
            ConcertEventRepository concertEventRepository,
            UserRepository userRepository
            ) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
        this.concertEventRepository = concertEventRepository;
        this.userRepository = userRepository;
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
    public Set<GroupDTO> getAllOpenGroupsByConcertEventId(String eventId) {
        ConcertEvent concertEvent =
                concertEventRepository.findById(UUID.fromString(eventId)).orElseThrow(() -> new EntityNotFoundException("Concert event with id " + eventId + " not found!"));
        var openGroupsByConcertEvent =
                groupRepository.findAllByOpenToNonInvitedIsTrueAndEvent(concertEvent);

        return openGroupsByConcertEvent.stream().map(groupMapper::mapGroupToGroupDTO).collect(Collectors.toSet());
    }

    @Override
    public Set<GroupDTO> getAllInvitedGroupsByUserId(String userId) {
        User user =
                userRepository.findById(UUID.fromString(userId)).orElseThrow(() -> new EntityNotFoundException("User with id " + userId + " not found!"));
        var invitedGroups = groupRepository.findAllByInvitedContaining(user);

        return invitedGroups.stream().map(groupMapper::mapGroupToGroupDTO).collect(Collectors.toSet());
    }

    @Override
    public boolean inviteUserToGroup(String userId, String groupId) {
        Group group =
                groupRepository.findById(UUID.fromString(groupId)).orElseThrow(() -> new EntityNotFoundException("Group with id: " + groupId + " not found!"));
        var invitedList = group.getInvited();

        User user =
                userRepository.findById(UUID.fromString(userId)).orElseThrow(() -> new EntityNotFoundException("User with id: " + userId + " not found!"));

        invitedList.add(user);
        group.setInvited(invitedList);

        groupRepository.save(group);

        return true;
    }

    @Override
    public boolean acceptInvite(String userId, String groupId) {
        return false;
    }
}
