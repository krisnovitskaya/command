package ru.geekbrains.javacommand.command.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AliveController {

    @GetMapping
    public String isAlive(){
        return "I am alive!";
    }
}
