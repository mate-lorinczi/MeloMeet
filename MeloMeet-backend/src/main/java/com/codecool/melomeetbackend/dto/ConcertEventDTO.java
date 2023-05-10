package com.codecool.melomeetbackend.dto;

import com.codecool.melomeetbackend.model.Style;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class ConcertEventDTO extends EventDTO{

    private Set<String> styles;
}