package ru.geekbrains.javacommand.command.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import ru.geekbrains.javacommand.command.dtos.ErrandStatisticDto;
import ru.geekbrains.javacommand.command.entities.Employee;
import ru.geekbrains.javacommand.command.entities.User;
import ru.geekbrains.javacommand.command.exceptions.ResourceNotFoundException;
import ru.geekbrains.javacommand.command.services.contracts.*;
import ru.geekbrains.javacommand.command.util.ErrandFilter;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ErrandServiceFacade {
    private final ErrandService errandService;
    private final UserService userService;
    private final RoleService roleService;
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    @Transactional
    public List<ErrandStatisticDto> findAllByParams(MultiValueMap<String, String> params, Principal principal){
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("User " + principal.getName() + " doesn't exist"));
        Employee employee = employeeService.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Employee with username: " + principal.getName() + " doesn't exist"));

        if(!params.containsKey("department")){
            if(isMaster(user)){
                params.put("department", departmentService.getSubordinateDepartments(employee.getDepartment().getId()).stream().map(dto -> dto.getId().toString()).collect(Collectors.toList()));
            } else if(isEmployee(user)){
                params.put("department", List.of(employee.getDepartment().getId().toString()));
                params.put("employee", List.of(employee.getId().toString()));
            }
        }

        ErrandFilter filter = new ErrandFilter(params);

        return errandService.findAllByParams(filter.getSpec());
    }


    private boolean isAdmin(User user){
        return user.getListRoles().contains(roleService.findByName("ROLE_ADMIN"));
    }

    private boolean isMaster(User user){
        return user.getListRoles().contains(roleService.findByName("ROLE_MASTER"));
    }

    private boolean isEmployee(User user){
        return user.getListRoles().contains(roleService.findByName("ROLE_EMPLOYEE"));
    }
}
