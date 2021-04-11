package ru.geekbrains.javacommand.command.controllers.facade;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import ru.geekbrains.javacommand.command.dtos.ProfileDto;

import java.security.Principal;

import ru.geekbrains.javacommand.command.dtos.EmployeeDto;

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

    @DeleteMapping(value = "/delete/{id}")
    void deleteEmployee(@PathVariable("id") Long id);

    @GetMapping(value = "/get/{department_id}", produces = "application/json")
    List<EmployeeDto> getEmployeesFromDepartment(@PathVariable(name = "department_id") Long departmentId);

    @GetMapping(value = "/getCurrent", produces = "application/json")
    EmployeeDto getEmployee(Principal principal);
}
