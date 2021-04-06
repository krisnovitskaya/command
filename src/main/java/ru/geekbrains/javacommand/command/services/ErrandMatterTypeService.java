package ru.geekbrains.javacommand.command.services;

import ru.geekbrains.javacommand.command.dtos.ErrandMatterDto;
import ru.geekbrains.javacommand.command.entities.ErrandMatterType;

import java.util.List;

public interface ErrandMatterTypeService {

    List<ErrandMatterDto> findAll();
    ErrandMatterDto findById(Long id);
    ErrandMatterType convertToErrandMatterType(ErrandMatterDto errandMatterDto);
}
