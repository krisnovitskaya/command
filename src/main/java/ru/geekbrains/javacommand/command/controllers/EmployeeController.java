package ru.geekbrains.javacommand.command.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.javacommand.command.controllers.facade.EmployeeControllerApi;
import ru.geekbrains.javacommand.command.dtos.EmployeeDto;
import ru.geekbrains.javacommand.command.entities.Employee;
import ru.geekbrains.javacommand.command.services.EmployeeService;
import ru.geekbrains.javacommand.command.services.UserService;
import ru.geekbrains.javacommand.command.services.impl.EmployeeServiceImpl;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeController implements EmployeeControllerApi {
    private final EmployeeService employeeService;
    private final UserService userService;

    @Override
    public List<EmployeeDto> getEmployeesFromDepartment(Long departmentId) {
        return employeeService.findAllByDepartmentId(departmentId);
    }

    @Override
    public EmployeeDto getEmployee(Principal principal) {
        return employeeService.findByUserId( userService.findByUsername(principal.getName()).get().getId());
    }
}
