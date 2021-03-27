package ru.geekbrains.javacommand.command.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.dtos.PlaceTypeDto;
import ru.geekbrains.javacommand.command.repositories.PlaceTypeRepository;
import ru.geekbrains.javacommand.command.services.PlaceTypeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaceTypeServiceImpl implements PlaceTypeService {
    private  final PlaceTypeRepository placeTypeRepository;

    @Override
    public List<PlaceTypeDto> findAll() {
        return placeTypeRepository.findAll().stream().map(PlaceTypeDto::new).collect(Collectors.toList());
    }
}
