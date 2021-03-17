package ru.geekbrains.javacommand.command.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.javacommand.command.controllers.facade.UserControllerApi;

@RestController
@RequiredArgsConstructor
public class UserController implements UserControllerApi {
    //для смены пароля
}
