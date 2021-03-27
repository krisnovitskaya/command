package ru.geekbrains.javacommand.command.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.dtos.EmployeeDto;
import ru.geekbrains.javacommand.command.entities.Employee;
import ru.geekbrains.javacommand.command.repositories.EmployeeRepository;
import ru.geekbrains.javacommand.command.services.EmployeeService;

import java.util.EventObject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeDto> findAllByDepartmentId(Long id) {
        return employeeRepository.findAllByDepartmentId(id).stream().map(EmployeeDto::new).collect(Collectors.toList());
    }
}
