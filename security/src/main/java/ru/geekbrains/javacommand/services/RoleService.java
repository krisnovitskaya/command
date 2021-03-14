package ru.geekbrains.javacommand.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.entities.Role;
import ru.geekbrains.javacommand.repositories.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findRoleByName("ROLE_USER");
    }
}