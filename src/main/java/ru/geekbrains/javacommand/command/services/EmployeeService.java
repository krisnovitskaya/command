package ru.geekbrains.javacommand.command.services;

import ru.geekbrains.javacommand.command.dtos.EmployeeDTO;
import ru.geekbrains.javacommand.command.entities.Employee;
import ru.geekbrains.javacommand.command.entities.User;

import java.util.List;
import java.util.Optional;

import ru.geekbrains.javacommand.command.dtos.ProfileDto;
import ru.geekbrains.javacommand.command.dtos.EmployeeDto;
import ru.geekbrains.javacommand.command.entities.Employee;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDTO> findAll();

    Optional<Employee> findByUser(User user);

    ProfileDto getProfile(String name);

    void saveOrUpdate(EmployeeDto employeeDto);

    List<EmployeeDto> findAll();

    void deleteEmployee(Long id);

}
