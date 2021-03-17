package ru.geekbrains.javacommand.command.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

/** Created by Andrey Krylov 14.03.2021 */

@Entity
@Table(name = "errands_details")
@Data
@NoArgsConstructor
public class ErrandDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "matter_id", nullable = false)
    private ErrandMatterType matter;

    @ManyToOne
    @JoinColumn(name = "place_id", nullable = false)
    private Place place;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private Employee createdBy;

    @ManyToOne
    @JoinColumn(name = "confirmed_or_rejected_by", nullable = false)
    private Employee confirmedOrRejectedBy;

    @Column(name = "created_at")
    private Timestamp createdAt;

}
