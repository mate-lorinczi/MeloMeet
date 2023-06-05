package com.codecool.melomeetbackend.service.style;

import com.codecool.melomeetbackend.model.Style;
import com.codecool.melomeetbackend.service.dto.StyleDTO;

import java.util.Set;

public interface StyleService{

    Set<StyleDTO> getAllStyles();
}
