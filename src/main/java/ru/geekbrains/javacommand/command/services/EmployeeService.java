package ru.geekbrains.javacommand.command.services;

import ru.geekbrains.javacommand.command.dtos.ProfileDto;
import ru.geekbrains.javacommand.command.entities.Employee;

import java.util.List;

public interface EmployeeService {

    ProfileDto getProfile(String name);
    List<Employee> getAll();
}
