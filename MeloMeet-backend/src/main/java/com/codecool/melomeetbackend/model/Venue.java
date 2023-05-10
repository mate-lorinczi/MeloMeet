package com.codecool.melomeetbackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@Entity
@Table
@NoArgsConstructor
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID venueId;
    @Column
    private String city;
    @Column
    private String postalCode;
    @Column
    private String address;
    @Column
    private boolean isOpenAir;
}
