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

    @JsonProperty("name")
    private String name;

    @JsonProperty("middle_name")
    private String middleName;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("position_id")
    private Position position;

    @JsonProperty("department_id")
    private Department department;

    @JsonProperty("user_id")
    private User user;

    public EmployeeDto(Employee employee){
        this.id = employee.getId();
        this.name = employee.getName();
        this.middleName = employee.getMiddleName();
        this.surname = employee.getSurname();
        this.position = employee.getPosition();
        this.department = employee.getDepartment();
        this.user = employee.getUser();
    }

}
