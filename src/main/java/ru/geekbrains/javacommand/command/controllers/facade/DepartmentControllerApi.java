package ru.geekbrains.javacommand.command.controllers.facade;


import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.javacommand.command.entities.Department;

import java.util.List;

@RequestMapping("/api/v1/departments")
public interface DepartmentControllerApi {

    List<Department> getAllDepartments();
}
