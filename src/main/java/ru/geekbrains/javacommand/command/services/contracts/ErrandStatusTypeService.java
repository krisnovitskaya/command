package ru.geekbrains.javacommand.command.services.contracts;

import ru.geekbrains.javacommand.command.dtos.ErrandStatusTypeDto;
import ru.geekbrains.javacommand.command.entities.ErrandStatusType;


import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ErrandStatusTypeService {

    List<ErrandStatusTypeDto> findAll();

    ErrandStatusTypeDto findById(Long id);

    ErrandStatusType returnFromDto(ErrandStatusTypeDto errandStatusTypeDto);

    Optional<ErrandStatusType> findByStatus(String status);
}