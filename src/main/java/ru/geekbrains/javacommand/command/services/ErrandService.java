package ru.geekbrains.javacommand.command.services;

import ru.geekbrains.javacommand.command.dtos.ErrandDto;
import ru.geekbrains.javacommand.command.entities.Errand;

public interface ErrandService {
    ErrandDto saveOrUpdate(Errand errand);
}
