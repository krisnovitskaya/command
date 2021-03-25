package ru.geekbrains.javacommand.command.entities;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "errands_matter_types")
public class ErrandMatterType extends DefaultEntity{

    @Column(name = "matter")
    private String matter;
}
