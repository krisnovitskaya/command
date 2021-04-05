package ru.geekbrains.javacommand.command.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.entities.Place;
import ru.geekbrains.javacommand.command.repositories.PlaceRepository;
import ru.geekbrains.javacommand.command.services.PlaceService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {
    private final PlaceRepository repository;

    @Override
    public List<Place> getAll() {
        return repository.findAll();
    }
}
