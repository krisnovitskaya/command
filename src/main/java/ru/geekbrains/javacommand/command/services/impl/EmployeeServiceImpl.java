package ru.geekbrains.javacommand.command.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.dtos.EmployeeDto;
import ru.geekbrains.javacommand.command.dtos.ProfileDto;
import ru.geekbrains.javacommand.command.entities.Employee;
import ru.geekbrains.javacommand.command.repositories.EmployeeRepository;
import ru.geekbrains.javacommand.command.services.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public ProfileDto getProfile(String username) {
        return new ProfileDto(employeeRepository.findEmployeeByUsername(username));
    }

    @Override
    public List<EmployeeDto> findAllByDepartmentId(Long id) {
        List<EmployeeDto> list = employeeRepository.findAllByDepartmentId(id).stream().map(EmployeeDto::new).collect(Collectors.toList());
        for(EmployeeDto e : list){
            System.out.println(e.getFirstName());
            System.out.println(e.getLastName());
            System.out.println(e.getPosition().getId());
        }
        return employeeRepository.findAllByDepartmentId(id).stream().map(EmployeeDto::new).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto findByUserId(Long id) {
        return new EmployeeDto(employeeRepository.findByUserId(id));
    }
}
