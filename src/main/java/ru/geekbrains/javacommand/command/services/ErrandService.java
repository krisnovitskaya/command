package ru.geekbrains.javacommand.command.services;

import ru.geekbrains.javacommand.command.dtos.CurrentErrandDto;

import java.util.List;

public interface ErrandService {
    List<CurrentErrandDto> getListCurrent();
}
