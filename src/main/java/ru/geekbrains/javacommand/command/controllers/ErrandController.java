package ru.geekbrains.javacommand.command.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.javacommand.command.controllers.facade.ErrandControllerApi;

@RestController
@RequiredArgsConstructor
public class ErrandController implements ErrandControllerApi {
}
