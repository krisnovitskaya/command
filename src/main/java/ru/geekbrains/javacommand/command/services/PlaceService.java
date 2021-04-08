package ru.geekbrains.javacommand.command.services;

import ru.geekbrains.javacommand.command.dtos.PlaceDto;
import ru.geekbrains.javacommand.command.entities.Place;

import java.util.List;

public interface PlaceService {

		List<Place> getAll();
    List<PlaceDto> findAllByPlaceTypeId(Long id);
    PlaceDto findById(Long id);
    Place convertToPlace(PlaceDto placeDto);
}
