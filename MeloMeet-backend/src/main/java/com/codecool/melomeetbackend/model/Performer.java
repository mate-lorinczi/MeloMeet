package com.codecool.melomeetbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Performer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID performerId;
    @Column
    private String name;

    public Performer(String performer) {
        this.name = performer;
    }
}
