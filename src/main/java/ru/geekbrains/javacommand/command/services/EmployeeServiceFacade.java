package ru.geekbrains.javacommand.command.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.javacommand.command.dtos.EmployeeSimpleDto;
import ru.geekbrains.javacommand.command.entities.Employee;
import ru.geekbrains.javacommand.command.entities.User;
import ru.geekbrains.javacommand.command.exceptions.ResourceNotFoundException;
import ru.geekbrains.javacommand.command.services.contracts.EmployeeService;
import ru.geekbrains.javacommand.command.services.contracts.RoleService;
import ru.geekbrains.javacommand.command.services.contracts.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceFacade {
    private final UserService userService;
    private final EmployeeService employeeService;
    private final RoleService roleService;


    @Transactional
    public List<EmployeeSimpleDto> getEmployees(Long id, Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("User " + principal.getName() + " doesn't exist"));
        Employee employee = employeeService.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Employee with username: " + principal.getName() + " doesn't exist"));
        if(user.getListRoles().contains(roleService.findByName("ROLE_MASTER"))){
            return employeeService.findAllByDepartmentId(id).stream().map(EmployeeSimpleDto::new).collect(Collectors.toList());
        }else {
            return List.of(new EmployeeSimpleDto(employeeService.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Employee with username: " + principal.getName() + " doesn't exist"))));
        }
    }
}
