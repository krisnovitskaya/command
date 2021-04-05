package ru.geekbrains.javacommand.command.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.entities.Department;
import ru.geekbrains.javacommand.command.entities.Employee;
import ru.geekbrains.javacommand.command.repositories.DepartmentRepository;
import ru.geekbrains.javacommand.command.services.contracts.DepartmentService;

import java.lang.module.ResolutionException;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public Department findAllEmployeesByMaster(Employee master) {
        return departmentRepository.findByMaster(master).orElseThrow(() -> new ResolutionException("Department not found"));
    }
}
