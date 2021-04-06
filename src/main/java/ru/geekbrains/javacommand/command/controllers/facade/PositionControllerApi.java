package ru.geekbrains.javacommand.command.controllers.facade;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.javacommand.command.dtos.PositionDto;

import java.util.List;

@RequestMapping("/api/v1/positions")
public interface PositionControllerApi {

    @GetMapping(value = "/all", produces = "application/json")
    List<PositionDto> getPositions();

}
