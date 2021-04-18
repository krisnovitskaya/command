package ru.geekbrains.javacommand.command.controllers.facade;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.javacommand.command.dtos.DepartmentDto;
import ru.geekbrains.javacommand.command.dtos.DepartmentSimpleDto;

import java.security.Principal;
import java.util.List;

@RequestMapping("/api/v1/departments")
public interface DepartmentControllerApi {

    @GetMapping(value = "/all", produces = "application/json")
    List<DepartmentDto> getDepartments();


    /**
     * Check User ROLE_MASTER.
     * If true return list all subordinate departments include employee`s department
     * else
     * return list of one employee`s department
     * @author  krisnovitskaya
     * @param principal
     * @return List<DepartmentSimpleDto>
     */
    @GetMapping(value = "/subordinate", produces = "application/json")
    List<DepartmentSimpleDto> getSubordinateDepartments(Principal principal);
}
