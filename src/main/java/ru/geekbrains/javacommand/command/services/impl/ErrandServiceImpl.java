package ru.geekbrains.javacommand.command.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.dtos.CurrentErrandDto;
import ru.geekbrains.javacommand.command.repositories.ErrandRepository;
import ru.geekbrains.javacommand.command.services.ErrandService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ErrandServiceImpl implements ErrandService {
    private final ErrandRepository errandRepository;
    @Override
    public List<CurrentErrandDto> getListCurrent() {
        return errandRepository.findCurrent().stream().map(CurrentErrandDto::new).collect(Collectors.toList());
    }
}
