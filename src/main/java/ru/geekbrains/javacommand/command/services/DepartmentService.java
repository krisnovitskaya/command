package ru.geekbrains.javacommand.command.services;

import ru.geekbrains.javacommand.command.entities.Department;
import ru.geekbrains.javacommand.command.entities.Employee;

public interface DepartmentService {
    Department findAllEmployeesByMaster(Employee master);
}
