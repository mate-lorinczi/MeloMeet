package com.codecool.melomeetbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/styles")
public class StyleController {

    @GetMapping
    public ResponseEntity<?> getAllStyles() {

    }
}
