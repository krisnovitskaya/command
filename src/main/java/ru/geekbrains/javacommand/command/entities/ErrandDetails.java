package ru.geekbrains.javacommand.command.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/** Created by Andrey Krylov 14.03.2021 */

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "errands_details")
public class ErrandDetails extends DefaultEntity {

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
    @JoinColumn(name = "confirmed_or_rejected_by")
    private Employee confirmedOrRejectedBy;

}
