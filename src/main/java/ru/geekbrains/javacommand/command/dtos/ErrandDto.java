package ru.geekbrains.javacommand.command.dtos;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.geekbrains.javacommand.command.entities.Errand;

@Data
@AllArgsConstructor
public class ErrandDto {

  private Long id;
  private OffsetDateTime created;
  private OffsetDateTime updated;
  private String statusType;
  private OffsetDateTime dateStart;
  private OffsetDateTime dateEnd;
  private Long employeeId;
  private String employeeFIO;
  private String matter;
  private String place;
  private String placeType;
  private String comment;
  private Long createdById;
  private String createdByFIO;
  private Long confirmedOrRejectedById;
  private String confirmedOrRejectedByFIO;

  public ErrandDto(Errand errand) {
    this.id = errand.getId();
    this.created = errand.getCreated();
    this.updated = errand.getUpdated();
    this.statusType = errand.getStatusType().getStatus();
    this.dateStart = errand.getDateStart();
    this.dateEnd = errand.getDateEnd();
    this.employeeId = errand.getEmployee().getId();
    this.employeeFIO =
        errand.getEmployee().getFirstName()
            + " "
            + errand.getEmployee().getLastName()
            + " "
            + errand.getEmployee().getMiddleName();
    this.matter = errand.getErrandDetails().getMatter().getMatter();
    this.place = errand.getErrandDetails().getPlace().getTitle();
    this.placeType = errand.getErrandDetails().getPlace().getPlaceType().getType();
    this.comment = errand.getErrandDetails().getComment();
    this.createdById = errand.getErrandDetails().getCreatedBy().getId();
    this.createdByFIO =
        errand.getErrandDetails().getCreatedBy().getFirstName()
            + " "
            + errand.getErrandDetails().getCreatedBy().getLastName()
            + " "
            + errand.getErrandDetails().getCreatedBy().getMiddleName();
    this.confirmedOrRejectedById = errand.getErrandDetails().getConfirmedOrRejectedBy().getId();
    this.confirmedOrRejectedByFIO =
        errand.getErrandDetails().getConfirmedOrRejectedBy().getFirstName()
            + " "
            + errand.getErrandDetails().getConfirmedOrRejectedBy().getLastName()
            + " "
            + errand.getErrandDetails().getConfirmedOrRejectedBy().getMiddleName();
  }
}