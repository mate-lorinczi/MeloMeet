package com.codecool.melomeetbackend.controller;

import com.codecool.melomeetbackend.repository.StyleRepository;
import com.codecool.melomeetbackend.service.dto.StyleDTO;
import com.codecool.melomeetbackend.service.style.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
@RestController
@RequestMapping("/styles")
public class StyleController {

    private final StyleService styleService;

    @Autowired
    public StyleController(StyleService styleService) {
        this.styleService = styleService;
    }

    @GetMapping
    public ResponseEntity<?> getAllStyles() {
        Set<StyleDTO> allStyles = styleService.getAllStyles();



        return ResponseEntity.status(HttpStatus.OK).body(allStyles);
    }
}
