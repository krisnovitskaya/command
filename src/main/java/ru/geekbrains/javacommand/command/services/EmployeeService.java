package ru.geekbrains.javacommand.command.services;

import ru.geekbrains.javacommand.command.dtos.EmployeeDto;
import ru.geekbrains.javacommand.command.entities.Employee;
import ru.geekbrains.javacommand.command.entities.User;
import ru.geekbrains.javacommand.command.dtos.ProfileDto;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Optional<Employee> findByUser(User user);

    ProfileDto getProfile(String name);

    void saveOrUpdate(EmployeeDto employeeDto);

    List<EmployeeDto> findAll();

    void deleteEmployee(Long id);

    Optional<Employee> findById(Long confirmedOrRejectedById);
}
