package ru.geekbrains.javacommand.command.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "place_type")
public class PlaceType extends DefaultEntity {

    @Column(name = "type")
    private String type;

}
