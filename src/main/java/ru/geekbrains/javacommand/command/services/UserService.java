package ru.geekbrains.javacommand.command.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.geekbrains.javacommand.command.dtos.UserDto;
import ru.geekbrains.javacommand.command.entities.User;

import java.util.Optional;

public interface UserService extends UserDetailsService {
   // UserDto findByUsername(String username);
   Optional<User> findByUsername(String username);
}
