package ru.geekbrains.javacommand.command;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestSecuredController {

    @GetMapping()
    public String hello() {
        return "hello";
    }
}
