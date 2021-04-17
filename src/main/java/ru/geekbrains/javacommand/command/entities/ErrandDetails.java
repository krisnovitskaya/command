package ru.geekbrains.javacommand.command.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/** Created by Andrey Krylov 14.03.2021 */

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "errands_details")
public class ErrandDetails extends DefaultEntity {

    @ManyToOne
    @NotNull
    @JoinColumn(name = "matter_id")
    private ErrandMatterType matter;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "place_id")
    private Place place;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "created_by")
    private Employee createdBy;

    @ManyToOne
    @JoinColumn(name = "confirmed_or_rejected_by")
    private Employee confirmedOrRejectedBy;

    public ErrandDetails(ErrandMatterType matter, Place place, String comment, Employee createdBy){
        this.matter = matter;
        this.place = place;
        this.comment = comment;
        this.createdBy = createdBy;
    }

}