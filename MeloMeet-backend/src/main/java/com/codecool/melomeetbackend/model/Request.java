package com.codecool.melomeetbackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
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
    @ManyToOne
    @JoinColumn
    private User sender;
    @JoinColumn
    @ManyToOne
    private User receiver;
    @Column
    @CreationTimestamp
    private Timestamp dateOfCreation;
    @Column
    private boolean isRead;
}
