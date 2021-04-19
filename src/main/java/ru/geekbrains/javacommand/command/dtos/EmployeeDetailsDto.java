package ru.geekbrains.javacommand.command.dtos;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.javacommand.command.entities.EmployeeDetails;

@Data
@NoArgsConstructor
@JsonAutoDetect
public class EmployeeDetailsDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("mail")
    private String mail;

    @JsonProperty("employee_id")
    private Long employeeId;

    public EmployeeDetailsDto(EmployeeDetails e) {
        this.id = e.getId();
        this.mail = e.getMail();
        this.employeeId = e.getEmployee().getId();
    }
}
