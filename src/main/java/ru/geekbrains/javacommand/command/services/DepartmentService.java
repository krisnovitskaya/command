package ru.geekbrains.javacommand.command.services;

import ru.geekbrains.javacommand.command.dtos.DepartmentDto;

import java.util.List;

public interface DepartmentService {

    List<DepartmentDto> findAll();

}
