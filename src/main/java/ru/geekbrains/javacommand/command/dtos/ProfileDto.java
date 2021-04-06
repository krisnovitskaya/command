package ru.geekbrains.javacommand.command.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.javacommand.command.entities.Employee;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {
    @JsonProperty("fio")
    private String fio;

    @JsonProperty("position")
    private String position;

    @JsonProperty("email")
    private String email;

    @JsonProperty("department")
    private String department;

    @JsonProperty("master")
    private String master;

    public ProfileDto(Employee employee){
        this.fio = employee.getFIO();
        this.department = employee.getDepartment().getTitle();
        this.position = employee.getPosition().getPosition();
        this.email = employee.getEmployeeDetails().getMail();
        this.master = employee.getDepartment().getMaster() != null ? employee.getDepartment().getMaster().getFIO() : null;
    }
}
