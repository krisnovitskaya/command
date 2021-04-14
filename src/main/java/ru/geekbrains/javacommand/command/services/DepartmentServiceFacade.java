package ru.geekbrains.javacommand.command.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.javacommand.command.dtos.DepartmentSimpleDto;
import ru.geekbrains.javacommand.command.entities.Employee;
import ru.geekbrains.javacommand.command.entities.User;
import ru.geekbrains.javacommand.command.exceptions.ResourceNotFoundException;
import ru.geekbrains.javacommand.command.services.contracts.DepartmentService;
import ru.geekbrains.javacommand.command.services.contracts.EmployeeService;
import ru.geekbrains.javacommand.command.services.contracts.RoleService;
import ru.geekbrains.javacommand.command.services.contracts.UserService;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceFacade {
    private final UserService userService;
    private final RoleService roleService;
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;


    /**
     * Check User ROLE_MASTER.
     * If true return list all subordinate departments include employee`s department
     * else
     * return list of one employee`s department
     * @author  krisnovitskaya
     * @param principal
     * @return List<DepartmentSimpleDto>
     */
    @Transactional
    public List<DepartmentSimpleDto> getDepartments(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("User " + principal.getName() + " doesn't exist"));
        Employee employee = employeeService.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Employee with username: " + principal.getName() + " doesn't exist"));

        if(user.getListRoles().contains(roleService.findByName("ROLE_MASTER"))){

            return departmentService.getSubordinateDepartments(employee.getDepartment().getId());
        } else {
            return List.of(new DepartmentSimpleDto(employee.getDepartment()));
        }
    }
}
