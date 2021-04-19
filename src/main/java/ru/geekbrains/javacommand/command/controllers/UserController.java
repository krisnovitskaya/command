package ru.geekbrains.javacommand.command.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.javacommand.command.controllers.facade.UserControllerApi;
import ru.geekbrains.javacommand.command.dtos.ChangePasswordDto;
import ru.geekbrains.javacommand.command.dtos.UserDto;
import ru.geekbrains.javacommand.command.entities.Role;
import ru.geekbrains.javacommand.command.entities.User;
import ru.geekbrains.javacommand.command.exceptions.PasswordUpdateError;
import ru.geekbrains.javacommand.command.exceptions.ResourceNotFoundException;
import ru.geekbrains.javacommand.command.repositories.RoleRepository;
import ru.geekbrains.javacommand.command.services.contracts.RoleService;
import ru.geekbrains.javacommand.command.services.contracts.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements UserControllerApi {
    private final UserService userService;
    private final BCryptPasswordEncoder encoder;
    private final RoleService roleService;


    public ResponseEntity<?> changePassword(@RequestBody @Validated ChangePasswordDto passwordDto, BindingResult bindingResult, Principal principal) {
        User currentUser = userService.findByUsername(principal.getName()).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find current user"));

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new PasswordUpdateError(bindingResult.getAllErrors()),HttpStatus.BAD_REQUEST);
        }

        if (!encoder.matches(passwordDto.getCurrentPass(), currentUser.getPassword())) {
            return new ResponseEntity<>(new PasswordUpdateError("Incorrect current password"), HttpStatus.BAD_REQUEST);

        }
        if(!passwordDto.getNewPass().equals(passwordDto.getRetypeNewPass())){
            return new ResponseEntity<>(new PasswordUpdateError("New password not equal retype password"),HttpStatus.BAD_REQUEST);
        }

        currentUser.setPassword(encoder.encode(passwordDto.getNewPass()));
        userService.save(currentUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Override
    public List<UserDto> getUsers() {
        return userService.findAll();
    }

    @Override
    public ResponseEntity<?> checkUserRoles(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException(String.format("User '%s' not found", principal.getName())));
        Role role = roleService.findById(2L);
        if (!user.getListRoles().contains(role)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public UserDto getUserToEdit(Long id) {
        return userService.findByEmployeeId(id);
    }

    @Override
    public void editUser(UserDto userDto) {
        userService.saveOrUpdate(userDto);
    }
}