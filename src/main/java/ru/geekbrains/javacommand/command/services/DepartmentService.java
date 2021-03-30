package ru.geekbrains.javacommand.command.services;

import ru.geekbrains.javacommand.command.entities.Department;

public interface DepartmentService {
    Department findByMaster(Long masterId);
}
