package ru.geekbrains.javacommand.command.dtos;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.javacommand.command.entities.Employee;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.javacommand.command.entities.Department;
import ru.geekbrains.javacommand.command.entities.Employee;
import ru.geekbrains.javacommand.command.entities.Position;
import ru.geekbrains.javacommand.command.entities.User;

import java.util.Optional;

@Data
@NoArgsConstructor
@JsonAutoDetect
public class EmployeeDto {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("middle_name")
    private String middleName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("position_name")
    private String positionName;
    @JsonProperty("department_name")
    private String departmentName;
    @JsonProperty("user_name")
    private String userName;
    private String mail;

		@JsonProperty("position_id")
    private Position position;
    @JsonProperty("department_id")
    private Department department;
    @JsonProperty("user_id")
    private User user;

    public EmployeeDto(Employee e) {
        this.id = e.getId();
        this.firstName = e.getFirstName();
        this.middleName = e.getMiddleName();
        this.lastName = e.getLastName();
        this.positionName = e.getPosition() != null ? e.getPosition().getPosition() : "def";
        this.departmentName = e.getDepartment() != null ? e.getDepartment().getTitle() : "def";
        this.userName = e.getUser() != null ? e.getUser().getUserName() : "def";
        this.mail = e.getEmployeeDetails() != null ? e.getEmployeeDetails().getMail() : "def";
    }

    public EmployeeDto(Optional<Employee> employee){
        this.id = employee.get().getId();
        this.firstName = employee.get().getFirstName();
        this.middleName = employee.get().getMiddleName();
        this.lastName = employee.get().getLastName();
        this.position = employee.get().getPosition();
//        this.department = employee.getDepartment();
//        this.user = employee.getUser();
    }
}
