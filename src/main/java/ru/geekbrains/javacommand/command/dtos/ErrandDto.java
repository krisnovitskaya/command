package ru.geekbrains.javacommand.command.dtos;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonFormat;
import ru.geekbrains.javacommand.command.entities.Errand;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrandDto {

    //TODO закомментированный код дублирует имеющийся

    private Long id;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private OffsetDateTime dateStart;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
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
//    private String detailMatterType;
//    private String detailPlace;
//    private String detailPlaceType;
//    private String detailComment;
    private String createdByFirstName;
    private String createdByMiddleName;
    private String createdByLastName;
    private String confirmedOrRejectedByFirstName;
    private String confirmedOrRejectedByMiddleName;
    private String confirmedOrRejectedByLastname;
    private String confirmedOrRejectedByFIO;
    private String createdByFIO;
    private String comment;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private OffsetDateTime created;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private OffsetDateTime updated;
    private String statusType;
    private Long employeeId;
    private String employeeFIO;
    private String matter;
    private String place;
    private String placeType;
    private Long createdById;
    private Long confirmedOrRejectedById;

    public ErrandDto(Errand errand) {
        this.id = errand.getId();
        this.created = errand.getCreated();
        this.updated = errand.getUpdated();
        this.statusType = errand.getStatusType().getStatus();
        this.dateStart = errand.getDateStart();
        this.dateEnd = errand.getDateEnd();
        this.employeeId = errand.getEmployee().getId();
        this.employeeFIO = errand.getEmployee().getLastName() + " "
                + errand.getEmployee().getFirstName() + " "
                + errand.getEmployee().getMiddleName();
        this.departmentTitle = errand.getEmployee().getDepartment().getTitle();
        this.matter = errand.getErrandDetails().getMatter().getMatter();
        this.place = errand.getErrandDetails().getPlace().getTitle();
        this.placeType = errand.getErrandDetails().getPlace().getTitle();
        this.comment = errand.getErrandDetails().getComment();
        this.createdById = errand.getErrandDetails().getCreatedBy().getId();
        this.createdByFIO = errand.getErrandDetails().getCreatedBy().getLastName() + " "
                + errand.getErrandDetails().getCreatedBy().getFirstName() + " "
                + errand.getErrandDetails().getCreatedBy().getMiddleName();
        this.confirmedOrRejectedById = errand.getErrandDetails().getConfirmedOrRejectedBy().getId();
        this.confirmedOrRejectedByFIO = errand.getErrandDetails().getConfirmedOrRejectedBy().getLastName() + " " +
                errand.getErrandDetails().getConfirmedOrRejectedBy().getFirstName() + " " +
                errand.getErrandDetails().getConfirmedOrRejectedBy().getMiddleName();

        this.employeeFirstName = errand.getEmployee().getFirstName();
        this.employeeMiddleName = errand.getEmployee().getMiddleName();
        this.employeeLastName = errand.getEmployee().getLastName();
        this.employeePosition = errand.getEmployee().getPosition().getPosition();
        this.employeeUserName = errand.getEmployee().getUser().getUserName();
        this.departmentTitle = errand.getEmployee().getDepartment().getTitle();
        this.departmentMasterFirstName = errand.getEmployee().getDepartment().getMaster().getFirstName();
        this.departmentMasterMiddleName = errand.getEmployee().getDepartment().getMaster().getMiddleName();
        this.departmentMasterLastName = errand.getEmployee().getDepartment().getMaster().getLastName();
//        this.detailMatterType = errand.getErrandDetails().getMatter().getMatter();
//        this.detailPlace = errand.getErrandDetails().getPlace().getTitle();
//        this.detailPlaceType = errand.getErrandDetails().getPlace().getPlaceType().getType();
//        this.detailComment = errand.getErrandDetails().getComment();
        this.createdByFirstName = errand.getErrandDetails().getCreatedBy().getFirstName();
        this.createdByMiddleName = errand.getErrandDetails().getCreatedBy().getMiddleName();
        this.createdByLastName = errand.getErrandDetails().getCreatedBy().getLastName();
        this.confirmedOrRejectedByFirstName = errand.getErrandDetails().getConfirmedOrRejectedBy().getFirstName();
        this.confirmedOrRejectedByMiddleName = errand.getErrandDetails().getConfirmedOrRejectedBy().getMiddleName();
        this.confirmedOrRejectedByLastname = errand.getErrandDetails().getConfirmedOrRejectedBy().getLastName();
    }
}
