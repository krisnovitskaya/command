package ru.geekbrains.javacommand.command.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.*;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "errands")
@NoArgsConstructor
public class Errands {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "created_at")
    private Timestamp createdAt;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "status_id")
    private ErrandsStatusType errandsStatusType;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "matter_id")
    private ErrandsMatterType errandsMatterType;

    @NotNull
    @Column(name = "date_start")
    private Timestamp dateStart;

    @Column(name = "date_end")
    private Timestamp dateEnd;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Employee createdBy;

    @NotNull
    @Column(name = "confirmed_or_rejected_by")
    private int confirmedBy;

}
