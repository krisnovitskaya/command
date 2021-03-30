package ru.geekbrains.javacommand.command.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.geekbrains.javacommand.command.entities.Employee;

/**
 * @author Andrey Krylov on 30.03.2021
 * @project command
 */
@Data
@AllArgsConstructor
public class EmployeeDTO {

    private Long id;
    private String lastName;
    private String firstName;
    private String middleName;

    public EmployeeDTO(Employee employee) {
        this.id = employee.getId();
        this.lastName = employee.getLastName();
        this.firstName = employee.getFirstName();
        this.middleName = employee.getMiddleName();
    }

}
