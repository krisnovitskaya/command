package ru.geekbrains.javacommand.command.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.javacommand.command.controllers.facade.DepartmentControllerApi;
import ru.geekbrains.javacommand.command.entities.Department;
import ru.geekbrains.javacommand.command.services.impl.DepartmentServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DepartmentController implements DepartmentControllerApi {
    private final DepartmentServiceImpl departmentService;

    @Override
    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.findAll();
    }
}
