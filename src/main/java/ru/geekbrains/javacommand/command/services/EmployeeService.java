package ru.geekbrains.javacommand.command.services;

import ru.geekbrains.javacommand.command.dtos.ProfileDto;

import ru.geekbrains.javacommand.command.dtos.EmployeeDto;
import ru.geekbrains.javacommand.command.entities.Employee;

public interface EmployeeService {

    ProfileDto getProfile(String name);

    EmployeeDto saveOrUpdate(Employee employee);

}
