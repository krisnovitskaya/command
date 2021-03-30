package ru.geekbrains.javacommand.command.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.javacommand.command.controllers.facade.ErrandControllerApi;
import ru.geekbrains.javacommand.command.dtos.ErrandDto;
import ru.geekbrains.javacommand.command.dtos.ErrandMatterDto;
import ru.geekbrains.javacommand.command.services.ErrandMatterTypeService;
import ru.geekbrains.javacommand.command.services.ErrandService;
import ru.geekbrains.javacommand.command.util.ErrandFilter;
import ru.geekbrains.javacommand.command.util.PageImpl;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/errands")
@RequiredArgsConstructor
public class ErrandController implements ErrandControllerApi {

    private final ErrandMatterTypeService matterTypeService;
    private final ErrandService errandService;

    @Override
    public ErrandDto findById(Long id) {
        return errandService.findErrandById(id);
    }

    @Override
    public List<ErrandMatterDto> getMatters() {
        return matterTypeService.findAll();
    }

    //TODO изменить формат вывода даты на читаемый
    @GetMapping(value = "/pending", produces = "application/json")
    public PageImpl<ErrandDto> getAllProducts(@RequestParam(defaultValue = "1", name = "p") Integer page,
                                              @RequestParam Map<String, String> params
    ) {
        if (page < 1) {
            page = 1;
        }
        ErrandFilter errandFilter = new ErrandFilter(params);
        return errandService.findAll(errandFilter.getSpec(), page - 1, 5);
    }

    @GetMapping(value = "/types", produces = "application/json")
    public List<ErrandMatterDto> getErrandMatters() {
        return getMatters();
    }
}
