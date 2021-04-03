package ru.geekbrains.javacommand.command.entities;

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
    private Employee employee;

    @OneToOne
    @JoinColumn(name = "errand_detail_id")
    private ErrandDetails errandDetails;

    @NotNull
    @Column(name = "date_start")
    private OffsetDateTime dateStart;

    @Column(name = "date_end")
    private OffsetDateTime dateEnd;

    @NotNull
    @Column(name = "deleted")
    private int deleted;

    public Errand(Employee employee, ErrandDetails errandDetails, OffsetDateTime dateStart, OffsetDateTime dateEnd){
        this.employee = employee;
        this.errandDetails = errandDetails;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

}
