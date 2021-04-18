package ru.geekbrains.javacommand.command.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.javacommand.command.controllers.facade.PositionControllerApi;
import ru.geekbrains.javacommand.command.dtos.PositionDto;
import ru.geekbrains.javacommand.command.services.contracts.PositionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PositionController implements PositionControllerApi {

    private final PositionService positionService;

    @Override
    public List<PositionDto> getPositions() {
        return positionService.findAll();
    }

}
