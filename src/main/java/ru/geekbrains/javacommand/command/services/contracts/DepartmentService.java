package ru.geekbrains.javacommand.command.services.contracts;

import java.util.List;
import ru.geekbrains.javacommand.command.dtos.DepartmentDto;
import ru.geekbrains.javacommand.command.dtos.DepartmentSimpleDto;
import ru.geekbrains.javacommand.command.entities.Department;
import ru.geekbrains.javacommand.command.entities.Employee;
import ru.geekbrains.javacommand.command.entities.User;

public interface DepartmentService {

    List<DepartmentDto> findAll();

    Department findAllEmployeesByMaster(Employee master);

    Department findDepartmentByDepartmentTitle(String departmentTitle);


    List<DepartmentSimpleDto> getSubordinateDepartments(Long id);

}
