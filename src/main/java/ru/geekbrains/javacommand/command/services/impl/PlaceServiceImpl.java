package ru.geekbrains.javacommand.command.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.dtos.PlaceDto;
import ru.geekbrains.javacommand.command.entities.Place;
import ru.geekbrains.javacommand.command.exceptions.ResourceNotFoundException;
import ru.geekbrains.javacommand.command.repositories.PlaceRepository;
import ru.geekbrains.javacommand.command.services.contracts.PlaceService;

import javax.validation.constraints.NotNull;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {
    private final PlaceRepository placeRepository;

    @Override
    public List<Place> getAll() {
        return placeRepository.findAll();
		}

	@Override
    public List<PlaceDto> findAllByPlaceTypeId(@NotNull Long id) {
        return placeRepository.findAllByPlaceTypeId(id).stream().map(PlaceDto::new).collect(Collectors.toList());
    }

    @Override
    public PlaceDto findById(@NotNull Long id) {
        return new PlaceDto(placeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Place with id = " + id + "not exist" )));
    }

    @Override
    public Place convertToPlace(PlaceDto placeDto){
        Place place = new Place();
        place.setId(place.getId());
        place.setTitle(placeDto.getTitle());
        return  place;
    }
}
