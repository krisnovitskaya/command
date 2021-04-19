package ru.geekbrains.javacommand.command.services.contracts;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.geekbrains.javacommand.command.dtos.UserDto;

import java.util.List;
import ru.geekbrains.javacommand.command.entities.User;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    Optional<User> findByUsername(String name);

    User save(User user);

    UserDetails loadUserByUsername(String username);

    List<UserDto> findAll();

    boolean isAdmin(User user);

    boolean isMaster(User user);

}
