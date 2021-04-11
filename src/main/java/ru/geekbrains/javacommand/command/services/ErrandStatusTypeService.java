package ru.geekbrains.javacommand.command.services;

import ru.geekbrains.javacommand.command.dtos.ErrandStatusTypeDto;
import ru.geekbrains.javacommand.command.entities.ErrandStatusType;

import java.util.List;

public interface ErrandStatusTypeService {
    List<ErrandStatusType> getAll();
    ErrandStatusTypeDto findById(Long id);
    ErrandStatusType returnFromDto(ErrandStatusTypeDto errandStatusTypeDto);
}
