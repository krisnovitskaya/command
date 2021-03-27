package ru.geekbrains.javacommand.command.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.javacommand.command.entities.Employee;
import ru.geekbrains.javacommand.command.entities.ErrandDetails;
import ru.geekbrains.javacommand.command.entities.ErrandMatterType;
import ru.geekbrains.javacommand.command.entities.Place;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrandDetailsDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("matter_id")
    private ErrandMatterType matter;

    @JsonProperty("place_id")
    private Place place;

    @JsonProperty("comment")
    private String comment;

    @JsonProperty("created_by")
    private Employee createdBy;

    @JsonProperty("confirmed_or_rejected_by")
    private Employee confirmedOrRejectedBy;

    public ErrandDetailsDto(ErrandMatterType matter, Place place, String comment, Employee createdBy, Employee confirmedOrRejectedBy){
        this.matter = matter;
        this.place = place;
        this.comment = comment;
        this.createdBy = createdBy;
        this.confirmedOrRejectedBy = confirmedOrRejectedBy;
    }

    public ErrandDetailsDto(ErrandDetails errandDetails){
        this.matter = errandDetails.getMatter();
        this.place = errandDetails.getPlace();
        this.comment = errandDetails.getComment();
        this.createdBy = errandDetails.getCreatedBy();
    }
}
