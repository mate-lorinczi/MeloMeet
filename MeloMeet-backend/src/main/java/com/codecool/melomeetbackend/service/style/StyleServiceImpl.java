package com.codecool.melomeetbackend.service.style;

import com.codecool.melomeetbackend.repository.StyleRepository;
import com.codecool.melomeetbackend.service.dto.StyleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StyleServiceImpl implements StyleService{

    private final StyleRepository styleRepository;

    @Autowired
    public StyleServiceImpl(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    @Override
    public Set<StyleDTO> getAllStyles() {

        return styleRepository.findAll().stream().map(style -> new StyleDTO(style.getName(),
                style.getStyleId())).collect(Collectors.toSet());
    }
}
