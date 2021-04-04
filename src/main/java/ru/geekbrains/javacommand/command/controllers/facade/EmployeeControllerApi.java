package ru.geekbrains.javacommand.command.controllers.facade;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import ru.geekbrains.javacommand.command.dtos.ProfileDto;

import java.security.Principal;
import ru.geekbrains.javacommand.command.dtos.DepartmentDto;
import ru.geekbrains.javacommand.command.dtos.EmployeeDto;
import ru.geekbrains.javacommand.command.dtos.PositionDto;
import ru.geekbrains.javacommand.command.dtos.UserDto;
import ru.geekbrains.javacommand.command.entities.Employee;

import java.util.List;

@CrossOrigin("http://localhost:63342/")
@RequestMapping("/api/v1/employees")
public interface EmployeeControllerApi {

    @GetMapping(value = "/profile", produces = "application/json")
    ProfileDto getProfile(Principal principal);

    @GetMapping(value = "/all", produces = "application/json")
    List<EmployeeDto> getEmployees();

    @PostMapping
    void createEmployee(@RequestBody EmployeeDto employeeDto);
}
