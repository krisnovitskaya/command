package ru.geekbrains.javacommand.command.services.contracts;

import ru.geekbrains.javacommand.command.dtos.RoleDto;

import java.util.List;

public interface RoleService {

    List<RoleDto> findAll();

}
