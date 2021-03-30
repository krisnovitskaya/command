package ru.geekbrains.javacommand.command.services;

import ru.geekbrains.javacommand.command.dtos.EmployeeDTO;
import ru.geekbrains.javacommand.command.entities.Employee;
import ru.geekbrains.javacommand.command.entities.User;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Optional<Employee> findByUser(User user);

    List<EmployeeDTO> findAllByMaster(Long id);
}
