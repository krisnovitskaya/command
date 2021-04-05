/*
 * License Headers.
 */

package ru.geekbrains.javacommand.command.dtos;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.geekbrains.javacommand.command.entities.Errand;
import ru.geekbrains.javacommand.command.utils.CustomDateSerializer;

/** @author Igor Popovich, email: popovichia@gmail.com */
@Data
@AllArgsConstructor
public class ErrandDto {

  private Long id;
  private OffsetDateTime created;
  private OffsetDateTime updated;
  private String statusType;
  @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
  private OffsetDateTime dateStart;
  @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
  private OffsetDateTime dateEnd;
  private String employeeFirstName;
  private String employeeMiddleName;
  private String employeeLastName;
  private String employeePosition;
  private String employeeUserName;
  private String departmentTitle;
  private String departmentMasterFirstName;
  private String departmentMasterMiddleName;
  private String departmentMasterLastName;
  private String departmentMasterUserName;
  private String detailMatterType;
  private String detailPlace;
  private String detailPlaceType;
  private String detailComment;
  private String detailCreatedByFirstName;
  private String detailCreatedByMiddleName;
  private String detailCreatedByLastName;
  private String detailConfirmedOrRejectedByFirstName;
  private String detailConfirmedOrRejectedByMiddleName;
  private String detailConfirmedOrRejectedByLastname;

  public ErrandDto(Errand errand) {
    this.id = errand.getId();
    this.created = errand.getCreated();
    this.updated = errand.getUpdated();
    this.statusType = errand.getStatusType().getStatus();
    this.dateStart = errand.getDateStart();
    this.dateEnd = errand.getDateEnd();
    this.employeeFirstName = errand.getEmployee().getFirstName();
    this.employeeMiddleName = errand.getEmployee().getMiddleName();
    this.employeeLastName = errand.getEmployee().getLastName();
    this.employeePosition = errand.getEmployee().getPosition().getPosition();
    this.employeeUserName = errand.getEmployee().getUser().getUserName();
    this.departmentTitle = errand.getEmployee().getDepartment().getTitle();
    this.departmentMasterFirstName = errand.getEmployee().getDepartment().getMaster().getFirstName();
    this.departmentMasterMiddleName = errand.getEmployee().getDepartment().getMaster().getMiddleName();
    this.departmentMasterLastName = errand.getEmployee().getDepartment().getMaster().getLastName();
    this.detailMatterType = errand.getErrandDetails().getMatter().getMatter();
    this.detailPlace = errand.getErrandDetails().getPlace().getTitle();
    this.detailPlaceType = errand.getErrandDetails().getPlace().getPlaceType().getType();
    this.detailComment = errand.getErrandDetails().getComment();
    this.detailCreatedByFirstName = errand.getErrandDetails().getCreatedBy().getFirstName();
    this.detailCreatedByMiddleName = errand.getErrandDetails().getCreatedBy().getMiddleName();
    this.detailCreatedByLastName = errand.getErrandDetails().getCreatedBy().getLastName();
    this.detailConfirmedOrRejectedByFirstName = errand.getErrandDetails().getConfirmedOrRejectedBy().getFirstName();
    this.detailConfirmedOrRejectedByMiddleName = errand.getErrandDetails().getConfirmedOrRejectedBy().getMiddleName();
    this.detailConfirmedOrRejectedByLastname = errand.getErrandDetails().getConfirmedOrRejectedBy().getLastName();
  }
}
