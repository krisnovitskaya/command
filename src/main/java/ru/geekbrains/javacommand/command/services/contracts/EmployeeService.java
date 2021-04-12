package ru.geekbrains.javacommand.command.services.contracts;

import ru.geekbrains.javacommand.command.dtos.EmployeeDto;
import ru.geekbrains.javacommand.command.entities.User;
import ru.geekbrains.javacommand.command.dtos.ProfileDto;
import ru.geekbrains.javacommand.command.entities.Employee;


import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Optional<Employee> findByUser(User user);

    ProfileDto getProfile(String name);

    List<Employee> getAll();

    void saveOrUpdate(EmployeeDto employeeDto);

    List<EmployeeDto> findAll();

    void deleteEmployee(Long id);

    Employee findById(Long id);

    List<EmployeeDto> findAllByDepartmentId(Long id);

    EmployeeDto findByUsername(String username);
    
    EmployeeDto findByUserId(Long id);

    EmployeeDto findEmployeeById(Long id);

}
