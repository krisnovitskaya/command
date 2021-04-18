package ru.geekbrains.javacommand.command.services.contracts;

import ru.geekbrains.javacommand.command.dtos.ErrandDetailsDto;
import ru.geekbrains.javacommand.command.entities.ErrandDetails;

public interface ErrandDetailsService {
    ErrandDetailsDto saveOrUpdate(ErrandDetails errandDetails);
    void save(ErrandDetails errandDetails);
}
