package ru.geekbrains.javacommand.command.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.javacommand.command.controllers.facade.PostControllerApi;
import ru.geekbrains.javacommand.command.dtos.CurrentErrandDto;
import ru.geekbrains.javacommand.command.services.contracts.ErrandService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController implements PostControllerApi {
    private final ErrandService errandService;

    @Override
    public List<CurrentErrandDto> getListCurrent() {
        return errandService.getListCurrent();
    }
}
