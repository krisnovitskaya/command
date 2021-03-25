package ru.geekbrains.javacommand.command.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.javacommand.command.controllers.facade.ErrandControllerApi;
import ru.geekbrains.javacommand.command.dtos.CurrentErrandDto;
import ru.geekbrains.javacommand.command.dtos.ErrandMatterDto;
import ru.geekbrains.javacommand.command.services.ErrandMatterTypeService;
import ru.geekbrains.javacommand.command.services.ErrandService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ErrandController implements ErrandControllerApi {
    private final ErrandMatterTypeService matterTypeService;
    private final ErrandService errandService;


    @Override
    public List<ErrandMatterDto> getMatters() {
        return matterTypeService.findAll();
    }
}
