package ru.geekbrains.javacommand.command.services;

import ru.geekbrains.javacommand.command.dtos.EmployeeDto1;
import ru.geekbrains.javacommand.command.entities.Employee;
import ru.geekbrains.javacommand.command.entities.User;
import ru.geekbrains.javacommand.command.dtos.ProfileDto;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Optional<Employee> findByUser(User user);

    ProfileDto getProfile(String name);

    void saveOrUpdate(EmployeeDto1 employeeDto1);

    List<EmployeeDto1> findAll();

    void deleteEmployee(Long id);

}
