package ru.geekbrains.javacommand.command.dtos;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.geekbrains.javacommand.command.entities.Employee;

@Getter
@Setter
@NoArgsConstructor
@JsonAutoDetect
public class EmployeeDto {

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
}
