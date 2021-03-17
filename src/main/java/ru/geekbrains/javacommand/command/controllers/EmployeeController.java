package ru.geekbrains.javacommand.command.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.javacommand.command.controllers.facade.EmployeeControllerApi;

@RestController
@RequiredArgsConstructor
public class EmployeeController implements EmployeeControllerApi {
}
