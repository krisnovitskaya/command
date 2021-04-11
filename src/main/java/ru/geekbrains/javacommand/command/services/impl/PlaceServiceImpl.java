package ru.geekbrains.javacommand.command.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.dtos.PlaceDto;
import ru.geekbrains.javacommand.command.entities.Place;
import ru.geekbrains.javacommand.command.exceptions.ResourceNotFoundException;
import ru.geekbrains.javacommand.command.repositories.PlaceRepository;
import ru.geekbrains.javacommand.command.services.PlaceService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {
    private final PlaceRepository repository;

    @Override
    public List<Place> getAll() {
        return repository.findAll();
    }

    @Override
    public List<PlaceDto> findAllByPlaceTypeId(Long id) {
        return repository.findAllByPlaceTypeId(id).stream().map(PlaceDto::new).collect(Collectors.toList());
    }

    @Override
    public PlaceDto findById(Long id) {
        Place place = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("ErrandMatterType with id = " + id + " not found", id)));
        return new PlaceDto(place);
    }

    @Override
    public Place convertToPlace(PlaceDto placeDto){
        Place place = new Place();
        place.setId(place.getId());
        place.setTitle(placeDto.getTitle());
        return  place;
    }
}
