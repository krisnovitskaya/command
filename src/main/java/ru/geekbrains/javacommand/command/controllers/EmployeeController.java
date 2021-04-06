package ru.geekbrains.javacommand.command.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.javacommand.command.controllers.facade.EmployeeControllerApi;
import ru.geekbrains.javacommand.command.dtos.EmployeeDto;
import ru.geekbrains.javacommand.command.dtos.ProfileDto;
import ru.geekbrains.javacommand.command.entities.User;
import ru.geekbrains.javacommand.command.services.EmployeeService;
import ru.geekbrains.javacommand.command.services.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeController implements EmployeeControllerApi {
    private final EmployeeService employeeService;
    private final UserService userService;

    @Override
    public ProfileDto getProfile(Principal principal) {
        return employeeService.getProfile(principal.getName());
    }

    @Override
    public List<EmployeeDto> getEmployeesFromDepartment(Long departmentId) {
        return employeeService.findAllByDepartmentId(departmentId);
    }

    @Override
    public EmployeeDto getEmployee(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", principal.getName())));
        return employeeService.findByUserId(user.getId());
    }
}
