package ru.geekbrains.javacommand.command.controllers.facade;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RequestMapping("/api/alive")
public interface AliveControllerApi {

    @GetMapping
    String isAlive();

    @GetMapping(value = "/comments/{id}", produces = "application/json")
    String findById(@PathVariable("id") @NotNull Long id);
}
