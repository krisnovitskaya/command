package ru.geekbrains.javacommand.command.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.dtos.ErrandStatusTypeDto;
import ru.geekbrains.javacommand.command.entities.ErrandStatusType;
import ru.geekbrains.javacommand.command.repositories.ErrandStatusTypeRepository;
import ru.geekbrains.javacommand.command.services.ErrandStatusTypeService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ErrandStatusTypeServiceImpl implements ErrandStatusTypeService {
    private final ErrandStatusTypeRepository errandStatusTypeRepository;

    @Override
    public ErrandStatusTypeDto findById(Long id) {
        return new ErrandStatusTypeDto(errandStatusTypeRepository.getOne(1L));
    }

    @Override
    public ErrandStatusType returnFromDto(ErrandStatusTypeDto errandStatusTypeDto) {
        ErrandStatusType errandStatusType = new ErrandStatusType();
        errandStatusType.setId(errandStatusTypeDto.getId());
        errandStatusType.setStatus(errandStatusTypeDto.getStatus());
        return errandStatusType;
    }
}
