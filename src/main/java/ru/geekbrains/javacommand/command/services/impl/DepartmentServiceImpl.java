package ru.geekbrains.javacommand.command.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.dtos.DepartmentDto;
import ru.geekbrains.javacommand.command.exceptions.ResourceNotFoundException;
import ru.geekbrains.javacommand.command.repositories.DepartmentRepository;
import ru.geekbrains.javacommand.command.services.DepartmentService;
import ru.geekbrains.javacommand.command.entities.Department;
import ru.geekbrains.javacommand.command.entities.Employee;

import java.util.List;
import java.util.stream.Collectors;
import java.lang.module.ResolutionException;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentDto> findAll() {
        return departmentRepository.findAll().stream().map(DepartmentDto :: new).collect(Collectors.toList());
    }
    
    @Override
    public Department findAllEmployeesByMaster(Employee master) {
        return departmentRepository.findByMaster(master).orElseThrow(() -> new ResolutionException("Department not found"));
    }

    @Override
    public Department findDepartmentByDepartmentTitle(String departmentTitle) {
        return departmentRepository.findDepartmentByTitle(departmentTitle)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Department not found by title: %s", departmentTitle)));
    }
}
