package ru.geekbrains.javacommand.command.controllers.facade;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.javacommand.command.dtos.DepartmentDto;
import ru.geekbrains.javacommand.command.dtos.DepartmentSimpleDto;

import java.util.List;

@RequestMapping("/api/v1/departments")
public interface DepartmentControllerApi {

    @GetMapping(value = "/all", produces = "application/json")
    List<DepartmentDto> getDepartments();

//    @GetMapping(value = "/alldepartments", produces = "application/json")
//    List<DepartmentDto> getAllDepartments();

    @GetMapping(value = "/subordinate/{id}", produces = "application/json")
    List<DepartmentSimpleDto> getSubordinateDepartments(@PathVariable Long id);
}
