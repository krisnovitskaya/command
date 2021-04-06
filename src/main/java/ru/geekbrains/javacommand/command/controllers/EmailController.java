package ru.geekbrains.javacommand.command.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.javacommand.command.services.EmailService;

@Controller
@RequiredArgsConstructor
public class EmailController {

    private final EmailService service;

    @GetMapping("/email")
    ResponseEntity<?> sendEmail(@RequestParam String to) {
        service.sendSimpleMessage(to, "Alert", "Hello");
        return ResponseEntity.ok("Email send");
    }
}
