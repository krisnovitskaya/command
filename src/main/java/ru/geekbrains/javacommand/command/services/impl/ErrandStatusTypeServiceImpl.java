package ru.geekbrains.javacommand.command.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.entities.ErrandStatusType;
import ru.geekbrains.javacommand.command.repositories.ErrandStatusTypeRepository;
import ru.geekbrains.javacommand.command.services.ErrandStatusTypeService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ErrandStatusTypeServiceImpl implements ErrandStatusTypeService {
    private final ErrandStatusTypeRepository repository;

    @Override
    public List<ErrandStatusType> getAll() {
        return repository.findAll();
    }
}
