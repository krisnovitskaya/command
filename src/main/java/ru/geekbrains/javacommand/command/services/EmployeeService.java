package ru.geekbrains.javacommand.command.services;

import ru.geekbrains.javacommand.command.dtos.EmployeeDto;
import ru.geekbrains.javacommand.command.dtos.ProfileDto;
import ru.geekbrains.javacommand.command.entities.Employee;

import java.util.List;

public interface EmployeeService {

    ProfileDto getProfile(String name);

    List<EmployeeDto> findAllByDepartmentId(Long id);

    //  EmployeeDto findByUsername(String username);
    EmployeeDto findByUserId(Long id);

    EmployeeDto findById(Long id);

    Employee convertToEmployee(EmployeeDto employeeDto);
}
