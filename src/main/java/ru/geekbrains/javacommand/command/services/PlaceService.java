package ru.geekbrains.javacommand.command.services;

import ru.geekbrains.javacommand.command.dtos.PlaceDto;
import java.util.List;

public interface PlaceService {
    List<PlaceDto> findAllByPlaceTypeId(Long id);
}
