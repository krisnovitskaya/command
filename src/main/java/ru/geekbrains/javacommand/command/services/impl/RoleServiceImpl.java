package ru.geekbrains.javacommand.command.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.dtos.RoleDto;
import ru.geekbrains.javacommand.command.entities.Role;
import ru.geekbrains.javacommand.command.repositories.RoleRepository;
import ru.geekbrains.javacommand.command.services.contracts.RoleService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;

    public Role getUserRole() {
        return repository.findRoleByName("ROLE_USER");
    }

    @Override
    public List<RoleDto> findAll() {
        return repository.findAll().stream().map(RoleDto::new).collect(Collectors.toList());
    }
}
