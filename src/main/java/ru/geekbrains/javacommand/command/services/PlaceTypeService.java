package ru.geekbrains.javacommand.command.services;

import ru.geekbrains.javacommand.command.dtos.PlaceTypeDto;

import java.util.List;

public interface PlaceTypeService {
    List<PlaceTypeDto> findAll();
}
