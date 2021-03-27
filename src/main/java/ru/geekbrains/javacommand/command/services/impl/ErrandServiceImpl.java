package ru.geekbrains.javacommand.command.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.dtos.ErrandDto;
import ru.geekbrains.javacommand.command.entities.Errand;
import ru.geekbrains.javacommand.command.repositories.ErrandRepository;
import ru.geekbrains.javacommand.command.services.ErrandService;

@Service
@RequiredArgsConstructor
public class ErrandServiceImpl implements ErrandService {
    private final ErrandRepository errandRepository;

    @Override
    public ErrandDto saveOrUpdate(Errand errand) {
        Errand newErrand = errandRepository.save(errand);
        return new ErrandDto(newErrand);
    }
}
