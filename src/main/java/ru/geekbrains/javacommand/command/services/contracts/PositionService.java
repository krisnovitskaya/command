package ru.geekbrains.javacommand.command.services.contracts;

import ru.geekbrains.javacommand.command.dtos.PositionDto;

import java.util.List;

public interface PositionService {

    List<PositionDto> findAll();

}
