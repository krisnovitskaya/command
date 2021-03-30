package ru.geekbrains.javacommand.command.services;

import ru.geekbrains.javacommand.command.dtos.EmployeeDTO;
import ru.geekbrains.javacommand.command.entities.Employee;
import ru.geekbrains.javacommand.command.entities.User;

import java.util.List;
import java.util.Optional;

import ru.geekbrains.javacommand.command.dtos.ProfileDto;

public interface EmployeeService {
    Optional<Employee> findByUser(User user);

    ProfileDto getProfile(String name);
}
