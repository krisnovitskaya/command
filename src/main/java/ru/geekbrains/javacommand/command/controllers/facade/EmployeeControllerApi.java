package ru.geekbrains.javacommand.command.controllers.facade;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.javacommand.command.dtos.EmployeeDto;
import ru.geekbrains.javacommand.command.dtos.ProfileDto;

import java.security.Principal;
import java.util.List;

@RequestMapping("/api/v1/employees")
public interface EmployeeControllerApi {

    @GetMapping(value = "/profile", produces = "application/json")
    ProfileDto getProfile(Principal principal);

    @GetMapping(value = "/get/{department_id}", produces = "application/json")
    List<EmployeeDto> getEmployeesFromDepartment(@PathVariable(name = "department_id") Long departmentId);

    @GetMapping(value = "/getCurrent", produces = "application/json")
    EmployeeDto getEmployee(Principal principal);
}
