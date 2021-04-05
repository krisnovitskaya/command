package ru.geekbrains.javacommand.command.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.dtos.ErrandMatterDto;
import ru.geekbrains.javacommand.command.repositories.ErrandMatterTypeRepository;
import ru.geekbrains.javacommand.command.services.contracts.ErrandMatterTypeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ErrandMatterTypeServiceImpl implements ErrandMatterTypeService {
    private final ErrandMatterTypeRepository matterTypeRepository;

    @Override
    public List<ErrandMatterDto> findAll() {
        return matterTypeRepository.findAll().stream().map(ErrandMatterDto::new).collect(Collectors.toList());
    }
}
