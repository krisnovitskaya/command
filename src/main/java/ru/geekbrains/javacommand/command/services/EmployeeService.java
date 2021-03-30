package ru.geekbrains.javacommand.command.services;

import ru.geekbrains.javacommand.command.dtos.EmployeeDto;
import ru.geekbrains.javacommand.command.entities.Employee;

public interface EmployeeService {

    EmployeeDto saveOrUpdate(Employee employee);

}
