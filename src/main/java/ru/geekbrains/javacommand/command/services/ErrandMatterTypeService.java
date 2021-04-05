package ru.geekbrains.javacommand.command.services;

import ru.geekbrains.javacommand.command.dtos.ErrandMatterDto;

import java.util.Arrays;
import java.util.List;

public interface ErrandMatterTypeService {

    List<ErrandMatterDto> findAll();
}
