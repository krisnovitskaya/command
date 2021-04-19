package ru.geekbrains.javacommand.command.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.entities.Role;
import ru.geekbrains.javacommand.command.exceptions.ResourceNotFoundException;
import ru.geekbrains.javacommand.command.repositories.RoleRepository;
import ru.geekbrains.javacommand.command.services.contracts.RoleService;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;


    @Override
    public Role findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                String.format("Роль с id = %s не найдена", id))
        );
    }

    @Override
    public Role findByName(String name){
        return repository.getOneByName(name);
    }

}
