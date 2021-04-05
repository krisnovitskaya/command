package ru.geekbrains.javacommand.command.services;

import ru.geekbrains.javacommand.command.entities.Department;

import java.util.List;

public interface DepartmentService {
    public List<Department> findAll();
}
