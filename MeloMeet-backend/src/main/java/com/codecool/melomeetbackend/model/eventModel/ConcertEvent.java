package com.codecool.melomeetbackend.model.eventModel;

import com.codecool.melomeetbackend.model.Artist;
import com.codecool.melomeetbackend.model.Style;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

import java.util.Set;
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table
@NoArgsConstructor
@DiscriminatorValue("1")
public class ConcertEvent extends Event{

    @Column
    @ManyToMany
    @JoinColumn
    private Set<Artist> artists;
    @JoinColumn
    @ManyToMany
    private Set<Style> styles;
}
