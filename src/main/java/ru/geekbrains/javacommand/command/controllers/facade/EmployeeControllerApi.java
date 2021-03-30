package ru.geekbrains.javacommand.command.controllers.facade;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.javacommand.command.dtos.ProfileDto;

import java.security.Principal;
import ru.geekbrains.javacommand.command.dtos.DepartmentDto;
import ru.geekbrains.javacommand.command.dtos.EmployeeDto;
import ru.geekbrains.javacommand.command.dtos.PositionDto;
import ru.geekbrains.javacommand.command.dtos.UserDto;
import ru.geekbrains.javacommand.command.entities.Employee;

import java.util.List;

@RequestMapping("/api/v1/employees")
public interface EmployeeControllerApi {

    @GetMapping(value = "/profile", produces = "application/json")
    ProfileDto getProfile(Principal principal);

    @GetMapping(produces = "application/json")
    List<PositionDto> getPositions();

    @GetMapping(produces = "application/json")
    List<DepartmentDto> getDepartments();

    @GetMapping(produces = "application/json")
    List<UserDto> getUsers();

    @PostMapping
    EmployeeDto createEmployee(@RequestBody Employee employee);
}
