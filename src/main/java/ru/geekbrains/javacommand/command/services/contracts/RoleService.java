package ru.geekbrains.javacommand.command.services.contracts;

import ru.geekbrains.javacommand.command.entities.Role;

import ru.geekbrains.javacommand.command.dtos.RoleDto;

import java.util.List;

public interface RoleService {
    Role findById(Long id);
    Role findByName(String name);

    List<RoleDto> findAll();

}
