package ru.geekbrains.javacommand.command.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.javacommand.command.controllers.facade.PostControllerApi;
import ru.geekbrains.javacommand.command.dtos.ErrandDto;
import ru.geekbrains.javacommand.command.services.ErrandService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController implements PostControllerApi {
    private final ErrandService errandService;

    @Override
    public List<ErrandDto> getListCurrent() {
//    public List<CurrentErrandDto> getListCurrent() {
        return errandService.getListCurrent();
    }
}
