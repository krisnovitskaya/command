package ru.geekbrains.javacommand.command.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.javacommand.command.controllers.facade.EmployeeControllerApi;
import ru.geekbrains.javacommand.command.dtos.ProfileDto;
import ru.geekbrains.javacommand.command.services.EmployeeService;

import java.security.Principal;
import ru.geekbrains.javacommand.command.dtos.DepartmentDto;
import ru.geekbrains.javacommand.command.dtos.EmployeeDto;
import ru.geekbrains.javacommand.command.dtos.PositionDto;
import ru.geekbrains.javacommand.command.dtos.UserDto;
import ru.geekbrains.javacommand.command.entities.Employee;
import ru.geekbrains.javacommand.command.services.DepartmentService;
import ru.geekbrains.javacommand.command.services.EmployeeService;
import ru.geekbrains.javacommand.command.services.PositionService;
import ru.geekbrains.javacommand.command.services.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeController implements EmployeeControllerApi {
    private final EmployeeService employeeService;
    private final PositionService positionService;
    private final DepartmentService departmentService;
    private final UserService userService;

    @Override
    public ProfileDto getProfile(Principal principal) {
        return employeeService.getProfile(principal.getName());
    }

    @Override
    public List<PositionDto> getPositions() {
        return null;
    }

    @Override
    public List<DepartmentDto> getDepartments() {
        return null;
    }

    @Override
    public List<UserDto> getUsers() {
        return null;
    }

    @Override
    public EmployeeDto createEmployee(Employee employee) {
        return null;
    }

    private final PositionService positionService;
    private final DepartmentService departmentService;
    private final UserService userService;

    @Override
    public List<PositionDto> getPositions() {
        return null;
    }

    @Override
    public List<DepartmentDto> getDepartments() {
        return null;
    }

    @Override
    public List<UserDto> getUsers() {
        return null;
    }

    @Override
    public EmployeeDto createEmployee(Employee employee) {
        return null;
    }
}
