package ru.geekbrains.javacommand.command.services;

import ru.geekbrains.javacommand.command.dtos.ErrandMatterDto;

import java.util.List;
import ru.geekbrains.javacommand.command.entities.ErrandMatterType;

public interface ErrandMatterTypeService {

  List<ErrandMatterDto> findAll();
	ErrandMatterType findErrandMatterTypeById(Long id);
}
