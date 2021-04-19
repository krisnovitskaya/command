package ru.geekbrains.javacommand.command.dtos;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.javacommand.command.entities.Employee;

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
    @JsonProperty("mail")
    private String mail;

    public EmployeeDto(Employee e) {
        this.id = e.getId();
        this.firstName = e.getFirstName();
        this.middleName = e.getMiddleName();
        this.lastName = e.getLastName();
        this.positionName = e.getPosition() != null ? e.getPosition().getPosition() : null;
        this.departmentName = e.getDepartment() != null ? e.getDepartment().getTitle() : null;
        this.userName = e.getUser() != null ? e.getUser().getUserName() : null;
        this.mail = e.getEmployeeDetails() != null ? e.getEmployeeDetails().getMail() : null;
    }

}
