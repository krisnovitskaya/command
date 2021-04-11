package ru.geekbrains.javacommand.command.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.entities.Role;
import ru.geekbrains.javacommand.command.repositories.RoleRepository;
import ru.geekbrains.javacommand.command.services.contracts.RoleService;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;

    public Role getUserRole() {
        return repository.findRoleByName("ROLE_USER");
    }

}
