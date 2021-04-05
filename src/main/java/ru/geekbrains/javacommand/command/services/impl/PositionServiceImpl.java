package ru.geekbrains.javacommand.command.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.dtos.PositionDto;
import ru.geekbrains.javacommand.command.repositories.PositionRepository;
import ru.geekbrains.javacommand.command.services.PositionService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;

    @Override
    public List<PositionDto> findAll() {
        return positionRepository.findAll().stream().map(PositionDto :: new).collect(Collectors.toList());
    }
}
