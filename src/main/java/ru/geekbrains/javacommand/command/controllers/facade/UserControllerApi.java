package ru.geekbrains.javacommand.command.controllers.facade;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.javacommand.command.dtos.ChangePasswordDto;
import ru.geekbrains.javacommand.command.dtos.UserDto;

import java.security.Principal;
import java.util.List;

@RequestMapping("/api/v1/users")
public interface UserControllerApi {

    @PostMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<?> changePassword(@RequestBody @Validated ChangePasswordDto passwordDto, BindingResult bindingResult, Principal principal);

    @GetMapping(value = "/all", produces = "application/json")
    List<UserDto> getUsers();

    @GetMapping(value = "/checkRoles", produces = "application/json")
    ResponseEntity<?> checkUserRoles(Principal principal);

    @GetMapping(value = "/edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserDto getUserToEdit(@PathVariable(name = "id") Long id);

    @PostMapping(value = "/edit")
    void editUser(@RequestBody UserDto userDto);

}
