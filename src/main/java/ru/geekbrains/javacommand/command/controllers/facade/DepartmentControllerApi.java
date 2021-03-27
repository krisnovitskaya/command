package ru.geekbrains.javacommand.command.controllers.facade;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.javacommand.command.dtos.DepartmentDto;
import ru.geekbrains.javacommand.command.entities.Department;

import java.util.List;

@RequestMapping("/api/v1/departments")
public interface DepartmentControllerApi {

    @GetMapping(produces = "application/json")
    List<DepartmentDto> getAllDepartments();
}
