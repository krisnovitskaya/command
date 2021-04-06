package ru.geekbrains.javacommand.command.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "errands")
public class Errand extends DefaultEntity {

    @ManyToOne
    @JoinColumn(name = "status_id")
    private ErrandStatusType statusType;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonBackReference
    private Employee employee;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "errand_details_id")
    private ErrandDetails errandDetails;

    @NotNull
    @Column(name = "date_start")
    private OffsetDateTime dateStart;

    @Column(name = "date_end")
    private OffsetDateTime dateEnd;

    @NotNull
    @Column(name = "deleted")
    private boolean deleted;

}
