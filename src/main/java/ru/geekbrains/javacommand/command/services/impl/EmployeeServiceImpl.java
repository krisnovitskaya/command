package ru.geekbrains.javacommand.command.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.dtos.EmployeeDTO;
import ru.geekbrains.javacommand.command.entities.Department;
import ru.geekbrains.javacommand.command.entities.Employee;
import ru.geekbrains.javacommand.command.entities.User;
import ru.geekbrains.javacommand.command.repositories.EmployeeRepository;
import ru.geekbrains.javacommand.command.services.DepartmentService;
import ru.geekbrains.javacommand.command.dtos.ProfileDto;
import ru.geekbrains.javacommand.command.services.EmployeeService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;

    public List<EmployeeDTO> findAllByMaster(Employee master) {
        Department department = departmentService.findAllEmployeesByMaster(master);
        return employeeRepository.findAllByDepartment(department);
    }

    @Override
    public Optional<Employee> findByUser(User user) {
        return employeeRepository.findByUser(user);
    }
    @Override
    public ProfileDto getProfile(String username) {
        return new ProfileDto(employeeRepository.findEmployeeByUsername(username));
    }
}
