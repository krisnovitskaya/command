package ru.geekbrains.javacommand.command.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.entities.PlaceType;
import ru.geekbrains.javacommand.command.repositories.PlaceTypeRepository;
import ru.geekbrains.javacommand.command.services.PlaceTypeService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceTypeServiceImpl implements PlaceTypeService {
    private final PlaceTypeRepository repository;

    @Override
    public List<PlaceType> getAll() {
        return repository.findAll();
    }
}
