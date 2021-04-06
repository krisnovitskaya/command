package ru.geekbrains.javacommand.command.services;

import ru.geekbrains.javacommand.command.dtos.DepartmentDto;
import ru.geekbrains.javacommand.command.entities.Department;
import ru.geekbrains.javacommand.command.entities.Employee;

import java.util.List;

public interface DepartmentService {

    List<DepartmentDto> findAll();

    Department findAllEmployeesByMaster(Employee master);

    Department findDepartmentByDepartmentTitle(String departmentTitle);
}
