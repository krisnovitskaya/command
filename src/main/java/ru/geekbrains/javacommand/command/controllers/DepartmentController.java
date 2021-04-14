package ru.geekbrains.javacommand.command.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.javacommand.command.controllers.facade.DepartmentControllerApi;
import ru.geekbrains.javacommand.command.dtos.DepartmentDto;
import ru.geekbrains.javacommand.command.dtos.DepartmentSimpleDto;
import ru.geekbrains.javacommand.command.services.DepartmentServiceFacade;
import ru.geekbrains.javacommand.command.services.contracts.DepartmentService;

import java.security.Principal;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class DepartmentController implements DepartmentControllerApi {

    private final DepartmentServiceFacade departmentServiceFacade;
    private final DepartmentService departmentService;

    @Override
    public List<DepartmentDto> getDepartments() {
        return departmentService.findAll();
		}


    @Override
    public List<DepartmentSimpleDto> getSubordinateDepartments(Principal principal){

        return departmentServiceFacade.getDepartments(principal);
    }

}
