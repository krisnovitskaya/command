package ru.geekbrains.javacommand.command.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.javacommand.command.controllers.facade.AuthControllerApi;
import ru.geekbrains.javacommand.command.dtos.JwtRequest;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthControllerApi {

    @Override
    public ResponseEntity<?> createAuthToken(JwtRequest authRequest) {
        //TODO
        return null;
    }
}
