package ru.geekbrains.javacommand.command.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.javacommand.command.entities.Department;
import ru.geekbrains.javacommand.command.entities.Employee;
import ru.geekbrains.javacommand.command.entities.Position;
import ru.geekbrains.javacommand.command.entities.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("middle_name")
    private String middleName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("position_id")
    private Long positionId;

    @JsonProperty("department_id")
    private Long departmentId;
    @JsonProperty("user_id")
    private Long userId;

    public EmployeeDto(Employee employee) {
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.middleName = employee.getMiddleName();
        this.lastName = employee.getLastName();
        this.positionId = employee.getPosition().getId();
        this.departmentId = employee.getDepartment().getId();
        this.userId = employee.getUser().getId();
    }

}