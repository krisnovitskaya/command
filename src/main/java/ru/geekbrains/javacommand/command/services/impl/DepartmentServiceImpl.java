package ru.geekbrains.javacommand.command.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.entities.Department;
import ru.geekbrains.javacommand.command.repositories.DepartmentRepository;
import ru.geekbrains.javacommand.command.services.DepartmentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository repository;

    public List<Department> findAll() {
        return repository.findAll();
    }
}
