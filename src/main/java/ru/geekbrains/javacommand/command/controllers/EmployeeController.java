package ru.geekbrains.javacommand.command.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.javacommand.command.controllers.facade.EmployeeControllerApi;
import ru.geekbrains.javacommand.command.dtos.EmployeeDTO;
import ru.geekbrains.javacommand.command.entities.Employee;
import ru.geekbrains.javacommand.command.entities.Role;
import ru.geekbrains.javacommand.command.entities.User;
import ru.geekbrains.javacommand.command.exceptions.ResourceNotFoundException;
import ru.geekbrains.javacommand.command.services.DepartmentService;
import ru.geekbrains.javacommand.command.services.EmployeeService;
import ru.geekbrains.javacommand.command.services.UserService;

import java.security.Principal;
import ru.geekbrains.javacommand.command.dtos.DepartmentDto;
import ru.geekbrains.javacommand.command.dtos.EmployeeDto;
import ru.geekbrains.javacommand.command.dtos.PositionDto;
import ru.geekbrains.javacommand.command.dtos.UserDto;
import ru.geekbrains.javacommand.command.dtos.ProfileDto;
import ru.geekbrains.javacommand.command.entities.Employee;
import ru.geekbrains.javacommand.command.services.DepartmentService;
import ru.geekbrains.javacommand.command.services.EmployeeService;
import ru.geekbrains.javacommand.command.services.PositionService;
import ru.geekbrains.javacommand.command.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController implements EmployeeControllerApi {

    private final EmployeeService employeeService;
    private final UserService userService;
    private final DepartmentService departmentService;

    @GetMapping(value = "/by_master", produces = "application/json")
    public List<EmployeeDTO> getAllEmployeesByMaster(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("user not found"));

        for (Role role : user.getListRoles()) {
            if (role.getName().equals("ROLE_ADMIN")) {
                return employeeService.findAll();
            }
        }

        Employee master = employeeService.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("master not found"));
        return departmentService.findAllEmployeesByMaster(master).getEmployees().stream().map(EmployeeDTO::new).collect(Collectors.toList());
    }

    @Override
    public ProfileDto getProfile(Principal principal) {
        return employeeService.getProfile(principal.getName());
    }

    @Override
    public List<EmployeeDto> getEmployees() {
        return employeeService.findAll();
    }

    @Override
    public void createEmployee(EmployeeDto employeeDto) {
        employeeService.saveOrUpdate(employeeDto);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeService.deleteEmployee(id);
    }

}
