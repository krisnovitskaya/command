package ru.geekbrains.javacommand.command.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.dtos.PlaceDto;
import ru.geekbrains.javacommand.command.entities.Place;
import ru.geekbrains.javacommand.command.repositories.PlaceRepository;
import ru.geekbrains.javacommand.command.services.PlaceService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {
    private final PlaceRepository placeRepository;

    @Override
    public List<PlaceDto> findAllByPlaceTypeId(Long id) {
        return placeRepository.findAllByPlaceTypeId(id).stream().map(PlaceDto::new).collect(Collectors.toList());
    }

    @Override
    public PlaceDto findById(Long id) {
        return new PlaceDto(placeRepository.findById(id));
    }

    @Override
    public Place convertToPlace(PlaceDto placeDto){
        Place place = new Place();
        place.setId(place.getId());
        place.setTitle(placeDto.getTitle());
        return  place;
    }
}
