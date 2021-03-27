package ru.geekbrains.javacommand.command.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.javacommand.command.entities.Employee;
import ru.geekbrains.javacommand.command.entities.Errand;
import ru.geekbrains.javacommand.command.entities.ErrandStatusType;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrandDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("status_id")
    private ErrandStatusType statusType;

    @JsonProperty("employee_id")
    private Employee employee;

    @JsonProperty("date_start")
    private OffsetDateTime dateStart;

    @JsonProperty("date_end")
    private OffsetDateTime dateEnd;

    public ErrandDto(Errand errand) {
        this.id = errand.getId();
        this.statusType = errand.getStatusType();
        this.employee = errand.getEmployee();
        this.dateStart = errand.getDateStart();
        this.dateEnd = errand.getDateEnd();
    }
}
