package ru.geekbrains.javacommand.command.services.contracts;

import ru.geekbrains.javacommand.command.dtos.EmployeeDTO;
import ru.geekbrains.javacommand.command.entities.Employee;
import ru.geekbrains.javacommand.command.entities.User;

import java.util.List;
import java.util.Optional;

import ru.geekbrains.javacommand.command.dtos.ProfileDto;

public interface EmployeeService {

    List<EmployeeDTO> findAll();

    Optional<Employee> findByUser(User user);

    ProfileDto getProfile(String name);
}