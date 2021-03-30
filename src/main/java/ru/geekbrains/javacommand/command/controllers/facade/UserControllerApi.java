package ru.geekbrains.javacommand.command.controllers.facade;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.javacommand.command.dtos.ChangePasswordDto;

import java.security.Principal;

@RequestMapping("/api/v1/users")
public interface UserControllerApi {

    @PostMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<?> changePassword(@RequestBody @Validated ChangePasswordDto passwordDto, BindingResult bindingResult, Principal principal);
}
