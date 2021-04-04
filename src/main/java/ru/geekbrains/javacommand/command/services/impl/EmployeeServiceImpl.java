package ru.geekbrains.javacommand.command.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.dtos.ProfileDto;
import ru.geekbrains.javacommand.command.entities.Employee;
import ru.geekbrains.javacommand.command.exceptions.ResourceNotFoundException;
import ru.geekbrains.javacommand.command.repositories.*;
import ru.geekbrains.javacommand.command.dtos.EmployeeDto;
import ru.geekbrains.javacommand.command.entities.Employee;
import ru.geekbrains.javacommand.command.repositories.EmployeeRepository;
import ru.geekbrains.javacommand.command.services.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;

    @Override
    public ProfileDto getProfile(String username) {
        return new ProfileDto(employeeRepository.findEmployeeByUsername(username));
    }

    @Override
    public void saveOrUpdate(EmployeeDto employeeDto) {
        Employee newEmployee;
        if (employeeDto.getId() == null) {
            newEmployee = new Employee();
        } else {
            newEmployee = employeeRepository.findById(employeeDto.getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            String.format("Сотрудник с id = %s не найден", employeeDto.getId()))
                    );
        }
        newEmployee.setFirstName(employeeDto.getFirstName());
        newEmployee.setMiddleName(employeeDto.getMiddleName());
        newEmployee.setLastName(employeeDto.getLastName());
        newEmployee.setPosition(positionRepository.findById(employeeDto.getPositionId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Должность с id = %s не найдена", employeeDto.getPositionId()))
                )
        );
        newEmployee.setDepartment(departmentRepository.findById(employeeDto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Отдел с id = %s не найден", employeeDto.getDepartmentId()))
                )
        );
        newEmployee.setUser(userRepository.findById(employeeDto.getUserId())
            .orElseThrow(() -> new ResourceNotFoundException(
                String.format("Учётная запись с id = %s не найдена", employeeDto.getUserId()))
            )
        );
        employeeRepository.save(newEmployee);
    }

    @Override
    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll().stream().map(EmployeeDto::new).collect(Collectors.toList());
    }

}
