package ru.geekbrains.javacommand.command.services;

import ru.geekbrains.javacommand.command.dtos.EmployeeDto;
import ru.geekbrains.javacommand.command.entities.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<EmployeeDto> findAllByDepartmentId(Long id);
  //  EmployeeDto findByUsername(String username);
    EmployeeDto findByUserId(Long id);
}
