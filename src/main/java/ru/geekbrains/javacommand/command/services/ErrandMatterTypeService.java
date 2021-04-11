package ru.geekbrains.javacommand.command.services;

import org.springframework.data.jpa.repository.Query;
import ru.geekbrains.javacommand.command.dtos.ErrandMatterDto;

import java.util.Arrays;
import java.util.List;

import ru.geekbrains.javacommand.command.entities.ErrandMatterType;

public interface ErrandMatterTypeService {

    List<ErrandMatterDto> findAll();

    @Query("select e from errands_matter_types e where e.id = ?1")
    ErrandMatterType findErrandMatterTypeById(Long id);

    ErrandMatterDto findById(Long id);
    ErrandMatterType convertToErrandMatterType(ErrandMatterDto errandMatterDto);

}
