package ru.geekbrains.javacommand.command.dtos;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
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
  private Long employeeId;
  private String confirmedOrRejectedByFIO;
  private String createdByFIO;
  private String comment;

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
    this.confirmedOrRejectedByFIO = errand.getErrandDetails().getConfirmedOrRejectedBy().getFirstName() + " " +
              errand.getErrandDetails().getConfirmedOrRejectedBy().getLastName() + " " +
              errand.getErrandDetails().getConfirmedOrRejectedBy().getMiddleName();
    this.createdByFIO = errand.getErrandDetails().getCreatedBy().getFirstName() + " "
              + errand.getErrandDetails().getCreatedBy().getLastName() + " "
              + errand.getErrandDetails().getCreatedBy().getMiddleName();
    this.comment = errand.getErrandDetails().getComment();
  }
}
//=======
//private Long id;
//    private OffsetDateTime created;
//    private OffsetDateTime updated;
//    private String statusType;
//    private OffsetDateTime dateStart;
//    private OffsetDateTime dateEnd;
//    private Long employeeId;
//    private String employeeFIO;
//    private String matter;
//    private String place;
//    private String placeType;
//    private String comment;
//    private Long createdById;
//    private String createdByFIO;
//    private Long confirmedOrRejectedById;
//    private String confirmedOrRejectedByFIO;
//
//    public ErrandDto(Errand errand) {
//        this.id = errand.getId();
//        this.created = errand.getCreated();
//        this.updated = errand.getUpdated();
//        this.statusType = errand.getStatusType().getStatus();
//        this.dateStart = errand.getDateStart();
//        this.dateEnd = errand.getDateEnd();
//        this.employeeId = errand.getEmployee().getId();
//        this.employeeFIO = errand.getEmployee().getFirstName() + " "
//                + errand.getEmployee().getLastName() + " "
//                + errand.getEmployee().getMiddleName();
//        this.matter = errand.getErrandDetails().getMatter().getMatter();
//        this.place = errand.getErrandDetails().getPlace().getTitle();
//        this.placeType = errand.getErrandDetails().getPlace().getPlaceType().getType();
//        this.comment = errand.getErrandDetails().getComment();
//        this.createdById = errand.getErrandDetails().getCreatedBy().getId();
//        this.createdByFIO = errand.getErrandDetails().getCreatedBy().getFirstName() + " "
//                + errand.getErrandDetails().getCreatedBy().getLastName() + " "
//                + errand.getErrandDetails().getCreatedBy().getMiddleName();
//        this.confirmedOrRejectedById = errand.getErrandDetails().getConfirmedOrRejectedBy().getId();
//        this.confirmedOrRejectedByFIO = errand.getErrandDetails().getConfirmedOrRejectedBy().getFirstName() + " " +
//                        errand.getErrandDetails().getConfirmedOrRejectedBy().getLastName() + " " +
//                        errand.getErrandDetails().getConfirmedOrRejectedBy().getMiddleName();
//    }
//
//}
