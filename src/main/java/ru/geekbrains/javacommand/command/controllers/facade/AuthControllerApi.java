package ru.geekbrains.javacommand.command.controllers.facade;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.geekbrains.javacommand.command.dtos.JwtRequest;

// путь
public interface AuthControllerApi {
    @PostMapping("/auth")
    ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest);
}
