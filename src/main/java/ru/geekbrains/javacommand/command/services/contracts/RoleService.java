package ru.geekbrains.javacommand.command.services.contracts;

import ru.geekbrains.javacommand.command.entities.Role;

public interface RoleService {
    Role findById(Long id);
    Role findByName(String name);
}
