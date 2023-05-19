package com.codecool.melomeetbackend.utility.mappers.groupMapper;

import com.codecool.melomeetbackend.model.Group;
import com.codecool.melomeetbackend.model.User;
import com.codecool.melomeetbackend.model.eventModel.ConcertEvent;
import com.codecool.melomeetbackend.repository.ConcertEventRepository;
import com.codecool.melomeetbackend.repository.UserRepository;
import com.codecool.melomeetbackend.service.dto.group.GroupDTO;
import com.codecool.melomeetbackend.service.dto.group.NewGroupDTO;
import com.codecool.melomeetbackend.service.user.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GroupMapperImpl implements GroupMapper{

    private final UserRepository userRepository;
    private final ConcertEventRepository concertEventRepository;

    @Autowired
    public GroupMapperImpl(UserRepository userRepository, ConcertEventRepository concertEventRepository) {
        this.userRepository = userRepository;
        this.concertEventRepository = concertEventRepository;
    }

    @Override
    public Group mapNewGroupDTOToGroup(NewGroupDTO newGroupDTO) {
        Group group = new Group();

        User creator =
                userRepository.findById(UUID.fromString(newGroupDTO.creatorId())).
                        orElseThrow(() -> new EntityNotFoundException("User with id " + newGroupDTO.creatorId() + " not found!"));

        ConcertEvent concertEvent =
                concertEventRepository.findById(UUID.fromString(newGroupDTO.eventId())).orElseThrow(() -> new EntityNotFoundException("Event with id " + newGroupDTO.eventId() + " not found!"));

        group.setCreator(creator);
        group.setOpenToNonInvited(newGroupDTO.isOpen());
        group.setEvent(concertEvent);

        return group;
    }

    @Override
    public Group mapGroupDTOToGroup(GroupDTO groupDTO) {
        return null;
    }

    @Override
    public GroupDTO mapGroupToGroupDTO(Group group) {
        return null;
    }
}
