package com.codecool.melomeetbackend.model.eventModel;

import com.codecool.melomeetbackend.model.Style;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table
@NoArgsConstructor
@DiscriminatorValue("1")
public class ConcertEvent extends Event{

    @JoinColumn
    @ManyToMany
    private Set<Style> styles;
}
