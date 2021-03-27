package ru.geekbrains.javacommand.command.controllers.facade;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.javacommand.command.dtos.EmployeeDto;
import ru.geekbrains.javacommand.command.entities.Employee;

import java.util.List;

@RequestMapping("/api/v1/employees")
public interface EmployeeControllerApi {

    @GetMapping("/get/{department_id}")
    List<EmployeeDto> getEmployeesFromDepartment(@PathVariable(name = "department_id") Long departmentId);
}
