package ru.geekbrains.javacommand.command.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.javacommand.command.entities.Errand;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentErrandDto {

    @JsonProperty("fio")
    private String fio;
    @JsonProperty("date_start")
    private OffsetDateTime dateStart;
    @JsonProperty("date_end")
    private OffsetDateTime dateEnd;
    @JsonProperty("department")
    private String department;


    public CurrentErrandDto(Errand curErrand){
        this.fio = curErrand.getEmployee().getFIO();
        this.dateStart = curErrand.getDateStart();
        this.dateEnd = curErrand.getDateEnd();
        this.department = curErrand.getEmployee().getDepartment().getTitle();
    }
}
