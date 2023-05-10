package com.codecool.melomeetbackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table
@Data
@NoArgsConstructor
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID artistId;
    @Column
    private String name;
}
