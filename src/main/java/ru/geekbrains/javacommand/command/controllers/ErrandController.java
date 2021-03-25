package ru.geekbrains.javacommand.command.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.javacommand.command.controllers.facade.ErrandControllerApi;
import ru.geekbrains.javacommand.command.dtos.ErrandMatterDto;
import ru.geekbrains.javacommand.command.services.ErrandMatterTypeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ErrandController implements ErrandControllerApi {
    private final ErrandMatterTypeService matterTypeService;


    @Override
    public List<ErrandMatterDto> getMatters() {
        return matterTypeService.findAll();
    }
}
