package com.codecool.melomeetbackend.utility.mappers.groupMapper;

import com.codecool.melomeetbackend.model.Group;
import com.codecool.melomeetbackend.service.dto.group.GroupDTO;
import com.codecool.melomeetbackend.service.dto.group.NewGroupDTO;

public interface GroupMapper {

    Group mapNewGroupDTOToGroup(NewGroupDTO newGroupDTO);
    GroupDTO mapGroupToGroupDTO(Group group);
}
