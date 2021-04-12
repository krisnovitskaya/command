package ru.geekbrains.javacommand.command.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.dtos.ErrandDetailsDto;
import ru.geekbrains.javacommand.command.entities.ErrandDetails;
import ru.geekbrains.javacommand.command.repositories.ErrandDetailsRepository;
import ru.geekbrains.javacommand.command.services.contracts.ErrandDetailsService;

@Service
@RequiredArgsConstructor
public class ErrandDetailsServiceImpl implements ErrandDetailsService {
    private final ErrandDetailsRepository errandDetailsRepository;

    @Override
    public ErrandDetailsDto saveOrUpdate(ErrandDetails errandDetails) {
        ErrandDetails newErrandDetails = errandDetailsRepository.save(errandDetails);
        return new ErrandDetailsDto(newErrandDetails);
    }

    @Override
    public void save(ErrandDetails errandDetails) {
        errandDetailsRepository.save(errandDetails);
    }
}
