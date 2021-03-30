package ru.geekbrains.javacommand.command.controllers.facade;

import org.springframework.web.bind.annotation.*;
import ru.geekbrains.javacommand.command.dtos.ErrandDto;
import ru.geekbrains.javacommand.command.dtos.ErrandMatterDto;
import ru.geekbrains.javacommand.command.dtos.PlaceDto;
import ru.geekbrains.javacommand.command.dtos.PlaceTypeDto;
import ru.geekbrains.javacommand.command.entities.Errand;
import ru.geekbrains.javacommand.command.entities.ErrandDetails;

import java.util.List;

@RequestMapping("/api/v1/errands")
public interface ErrandControllerApi {

    @GetMapping("/getErrandMatterTypesList")
    List<ErrandMatterDto> getMatters();

    @GetMapping("/getPlaceTypesList")
    List<PlaceTypeDto> getPlaceTypes();

    @GetMapping("/getPlacesList/{placeType_id}")
    List<PlaceDto> getPlaces(@PathVariable(name = "placeType_id") Long placeTypeId);

    @PostMapping
    public ErrandDto createErrand(@RequestBody Errand errand, @RequestBody ErrandDetails errandDetails);

}
