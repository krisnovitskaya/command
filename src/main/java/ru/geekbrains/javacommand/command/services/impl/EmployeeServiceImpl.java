package ru.geekbrains.javacommand.command.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.dtos.EmployeeDto1;
import ru.geekbrains.javacommand.command.entities.Department;
import ru.geekbrains.javacommand.command.entities.Employee;
import ru.geekbrains.javacommand.command.exceptions.ResourceNotFoundException;
import ru.geekbrains.javacommand.command.repositories.*;
import ru.geekbrains.javacommand.command.entities.User;
import ru.geekbrains.javacommand.command.repositories.EmployeeRepository;
import ru.geekbrains.javacommand.command.services.DepartmentService;
import ru.geekbrains.javacommand.command.dtos.ProfileDto;
import ru.geekbrains.javacommand.command.services.EmployeeService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;

    private final DepartmentService departmentService;

    public List<EmployeeDto1> findAllByMaster(Employee master) {
        Department department = departmentService.findAllEmployeesByMaster(master);
        return employeeRepository.findAllByDepartment(department).stream().map(EmployeeDto1::new).collect(Collectors.toList());
    }

    @Override
    public Optional<Employee> findByUser(User user) {
        return employeeRepository.findByUser(user);
    }
    @Override
    public ProfileDto getProfile(String username) {
        return new ProfileDto(employeeRepository.findEmployeeByUsername(username));
    }

    @Override
    public void saveOrUpdate(EmployeeDto1 employeeDto1) {
        Employee newEmployee;
        if (employeeDto1.getId() == null) {
            newEmployee = new Employee();
        } else {
            newEmployee = employeeRepository.findById(employeeDto1.getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            String.format("Сотрудник с id = %s не найден", employeeDto1.getId()))
                    );
        }
        newEmployee.setFirstName(employeeDto1.getFirstName());
        newEmployee.setMiddleName(employeeDto1.getMiddleName());
        newEmployee.setLastName(employeeDto1.getLastName());
        newEmployee.setPosition(positionRepository.findPositionByPosition(employeeDto1.getPositionName()));
        newEmployee.setDepartment(departmentRepository.findDepartmentByTitle(employeeDto1.getDepartmentName()));
        newEmployee.setUser(userRepository.findByUserName(employeeDto1.getUserName())
            .orElseThrow(() -> new ResourceNotFoundException(
                String.format("Учётная запись с userName = %s не найдена", employeeDto1.getUserName()))
            )
        );
        employeeRepository.save(newEmployee);
    }

    @Override
    public List<EmployeeDto1> findAll() {
        return employeeRepository.findAll().stream().map(EmployeeDto1::new).collect(Collectors.toList());
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

}
