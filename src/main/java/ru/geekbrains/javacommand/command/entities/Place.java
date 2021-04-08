package ru.geekbrains.javacommand.command.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "places")
public class Place extends DefaultEntity {

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "place_type_id")
    private PlaceType placeType;
}