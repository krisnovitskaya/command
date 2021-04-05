package ru.geekbrains.javacommand.command.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.javacommand.command.controllers.facade.DepartmentControllerApi;
import ru.geekbrains.javacommand.command.dtos.DepartmentDto;
import ru.geekbrains.javacommand.command.services.DepartmentService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class DepartmentController implements DepartmentControllerApi {
    private final DepartmentService departmentService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DepartmentDto> findAll() {
        return departmentService.findAll().stream()
                .map(DepartmentDto::new)
                .collect(Collectors.toList());
    }
}
