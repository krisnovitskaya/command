package ru.geekbrains.javacommand.command.services.contracts;

import ru.geekbrains.javacommand.command.dtos.ErrandMatterDto;

import java.util.List;

public interface ErrandMatterTypeService {

    List<ErrandMatterDto> findAll();
}