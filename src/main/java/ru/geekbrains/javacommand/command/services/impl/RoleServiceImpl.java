package ru.geekbrains.javacommand.command.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.dtos.RoleDto;
import ru.geekbrains.javacommand.command.entities.Role;
import ru.geekbrains.javacommand.command.exceptions.ResourceNotFoundException;
import ru.geekbrains.javacommand.command.repositories.RoleRepository;
import ru.geekbrains.javacommand.command.services.contracts.RoleService;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<RoleDto> findAll() {
        return repository.findAll().stream().map(RoleDto::new).collect(Collectors.toList());
    }
}
