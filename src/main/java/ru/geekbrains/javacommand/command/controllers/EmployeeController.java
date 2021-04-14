package ru.geekbrains.javacommand.command.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.javacommand.command.controllers.facade.EmployeeControllerApi;
import ru.geekbrains.javacommand.command.dtos.EmployeeDto;
import ru.geekbrains.javacommand.command.dtos.ProfileDto;
import ru.geekbrains.javacommand.command.entities.Employee;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.javacommand.command.entities.Role;
import ru.geekbrains.javacommand.command.entities.User;
import ru.geekbrains.javacommand.command.exceptions.ResourceNotFoundException;
import ru.geekbrains.javacommand.command.services.contracts.DepartmentService;
import ru.geekbrains.javacommand.command.services.contracts.EmployeeService;
import ru.geekbrains.javacommand.command.services.contracts.UserService;
import java.security.Principal;

import java.util.stream.Collectors;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController implements EmployeeControllerApi {

    private final EmployeeService employeeService;
    private final UserService userService;
    private final DepartmentService departmentService;

    @GetMapping(value = "/by_master", produces = "application/json")
    public List<EmployeeDto> getAllEmployeesByMaster(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("user not found"));

        for (Role role : user.getListRoles()) {
            if (role.getName().equals("ROLE_ADMIN")) {
                return employeeService.findAll();
            }
        }

        Employee master = employeeService.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("master not found"));
        return departmentService.findAllEmployeesByMaster(master).getEmployees().stream().map(EmployeeDto::new).collect(Collectors.toList());
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

    public List<EmployeeDto> getEmployeesFromDepartment(Long departmentId) {
        return employeeService.findAllByDepartmentId(departmentId);
    }

    @Override
    public EmployeeDto getEmployee(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException(String.format("User '%s' not found", principal.getName())));
        return employeeService.findByUserId(user.getId());
    }

    @Override
    public EmployeeDto getEmployeeToEdit(Long id) {
        return employeeService.findEmployeeById(id);
    }
}
