package ru.geekbrains.javacommand.command.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Andrey Krylov on 30.03.2021
 * @project command
 */
@Data
@AllArgsConstructor
public class EmployeeDTO {

    private String lastName;
    private String firstName;
    private String middleName;

}
