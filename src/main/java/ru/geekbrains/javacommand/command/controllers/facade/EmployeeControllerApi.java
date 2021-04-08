package ru.geekbrains.javacommand.command.controllers.facade;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import ru.geekbrains.javacommand.command.dtos.ProfileDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.javacommand.command.dtos.EmployeeDto;
import ru.geekbrains.javacommand.command.dtos.ProfileDto;

import java.security.Principal;
import java.util.List;

import java.security.Principal;

import ru.geekbrains.javacommand.command.dtos.EmployeeDto;

import java.util.List;

@CrossOrigin("http://localhost:63342/")
@RequestMapping("/api/v1/employees")
public interface EmployeeControllerApi {

    @GetMapping(path = "/profile", produces = "application/json")
    ProfileDto getProfile(Principal principal);

    @GetMapping(path = "/all", produces = "application/json")
    List<EmployeeDto> getEmployees();

    @PostMapping(path = "/new")
    void createEmployee(@RequestBody EmployeeDto employeeDto);

    @DeleteMapping(path = "/delete/{id}")
    void deleteEmployee(@PathVariable("id") Long id);

		@GetMapping(path = "/get/{department_id}", produces = "application/json")
    List<EmployeeDto> getEmployeesFromDepartment(@PathVariable(name = "department_id") Long departmentId);

    @GetMapping(path = "/getCurrent", produces = "application/json")
    EmployeeDto getEmployee(Principal principal);
}
