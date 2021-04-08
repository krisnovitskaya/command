package ru.geekbrains.javacommand.command.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "errands_status_types")
public class ErrandStatusType extends DefaultEntity {

    @Column(name = "status")
    private String status;

}
