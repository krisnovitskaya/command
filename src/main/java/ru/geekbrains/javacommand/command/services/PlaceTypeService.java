package ru.geekbrains.javacommand.command.services;

import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.dtos.PlaceTypeDto;
import ru.geekbrains.javacommand.command.entities.PlaceType;

import java.util.List;

@Service
public interface PlaceTypeService {

    List<PlaceType> getAll();

    List<PlaceTypeDto> findAll();
}
