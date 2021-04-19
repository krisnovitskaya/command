package ru.geekbrains.javacommand.command.dtos;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.javacommand.command.entities.Errand;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrandStatisticDto {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("matter")
    private String matter;
    @JsonProperty("status")
    private String status;
    @JsonProperty("department")
    private String department;
    @JsonProperty("employee")
    private String employee;
    @JsonProperty("placeType")
    private String placeType;
    @JsonProperty("place")
    private String place;
    @JsonProperty("dateStart")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private OffsetDateTime dateStart;
    @JsonProperty("dateEnd")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private OffsetDateTime dateEnd;


    public ErrandStatisticDto(Errand errand) {
        this.id = errand.getId();
        this.matter = errand.getErrandDetails().getMatter().getMatter();
        this.status = errand.getStatusType().getStatus();
        this.department = errand.getEmployee().getDepartment().getTitle();
        this.employee = errand.getEmployee().getFIO();
        this.placeType = errand.getErrandDetails().getPlace().getPlaceType().getType();
        this.place = errand.getErrandDetails().getPlace().getTitle();
        this.dateStart = errand.getDateStart();
        this.dateEnd = errand.getDateStart();
    }
}
