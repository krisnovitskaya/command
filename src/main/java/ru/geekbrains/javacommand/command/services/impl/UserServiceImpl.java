package ru.geekbrains.javacommand.command.services.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.javacommand.command.dtos.UserDto;
import ru.geekbrains.javacommand.command.entities.Role;
import ru.geekbrains.javacommand.command.entities.User;
import ru.geekbrains.javacommand.command.exceptions.ResourceNotFoundException;
import ru.geekbrains.javacommand.command.repositories.RoleRepository;
import ru.geekbrains.javacommand.command.repositories.UserRepository;
import ru.geekbrains.javacommand.command.services.contracts.UserService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUserName(username);
				/*Есть проблема в приведении типов.
					.orElseThrow(() -> new ResourceNotFoundException("User not found by name: " + username));
				*/
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).get();
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), mapRolesToAuthorities(user.getListRoles()));
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(UserDto :: new).collect(Collectors.toList());
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public User saveUser(User user) {
        return save(user);
    }

    @Override
    public boolean isAdmin(User user) {
        for (Role role : user.getListRoles()) {
            if (role.getName().equals("ROLE_ADMIN")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isMaster(User user) {
        for (Role role : user.getListRoles()) {
            if (role.getName().equals("ROLE_MASTER")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public UserDto findByEmployeeId(Long id) {
        return new UserDto(userRepository.findUserByEmployee_Id(id));
    }

    @Override
    public void saveOrUpdate(UserDto userDto) {
        User newUser;
        newUser = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Аккаунт с id = %s не найден", userDto.getId()))
                );
        newUser.setUserName(userDto.getUserName());
        newUser.setPassword(userDto.getPassword());
        newUser.setListRoles(userDto.getRoles().stream()
                .map(s -> roleRepository.findRoleByName(s)).collect(Collectors.toSet()));
        userRepository.save(newUser);
    }

}
