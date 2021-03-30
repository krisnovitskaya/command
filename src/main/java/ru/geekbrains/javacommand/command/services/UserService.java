package ru.geekbrains.javacommand.command.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.geekbrains.javacommand.command.entities.User;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    Optional<User> findByUsername(String name);
}
