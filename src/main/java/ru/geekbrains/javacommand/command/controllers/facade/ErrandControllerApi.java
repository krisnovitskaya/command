package ru.geekbrains.javacommand.command.controllers.facade;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.javacommand.command.dtos.ErrandMatterDto;

import java.util.List;

@RequestMapping("/api/v1/errands")
public interface ErrandControllerApi {

    @GetMapping(value = "matters", produces = "application/json")
    List<ErrandMatterDto> getMatters();

}
