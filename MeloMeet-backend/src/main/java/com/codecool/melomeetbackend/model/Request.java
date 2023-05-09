package com.codecool.melomeetbackend.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table
@NoArgsConstructor
@Data
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID requestId;
    @Column
    private String link;
    @Column
    private String msg;
}
