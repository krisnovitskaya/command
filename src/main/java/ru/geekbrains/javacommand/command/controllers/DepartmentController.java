package ru.geekbrains.javacommand.command.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.javacommand.command.controllers.facade.DepartmentControllerApi;
import ru.geekbrains.javacommand.command.dtos.DepartmentDto;
import ru.geekbrains.javacommand.command.services.DepartmentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DepartmentController implements DepartmentControllerApi {

    private final DepartmentService departmentService;

    @Override
    public List<DepartmentDto> getDepartments() {
        return departmentService.findAll();
    }
}
