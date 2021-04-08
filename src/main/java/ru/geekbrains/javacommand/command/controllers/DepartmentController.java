package ru.geekbrains.javacommand.command.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.javacommand.command.controllers.facade.DepartmentControllerApi;
import ru.geekbrains.javacommand.command.dtos.DepartmentDto;
import ru.geekbrains.javacommand.command.entities.Department;
import ru.geekbrains.javacommand.command.services.DepartmentService;
import ru.geekbrains.javacommand.command.services.impl.DepartmentServiceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequiredArgsConstructor
public class DepartmentController implements DepartmentControllerApi {
    
		@Autowired
    private DepartmentService departmentService;

    @Override
    public List<DepartmentDto> getDepartments() {
        return departmentService.findAll();
		}
		
		@Override
    public List<DepartmentDto> getAllDepartments() {
        return departmentService.findAll();
    }
}
