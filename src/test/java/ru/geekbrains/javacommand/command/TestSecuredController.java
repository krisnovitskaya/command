package ru.geekbrains.javacommand.command;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticated")
public class TestSecuredController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
