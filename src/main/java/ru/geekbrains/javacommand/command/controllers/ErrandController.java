package ru.geekbrains.javacommand.command.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.javacommand.command.controllers.facade.ErrandControllerApi;
import ru.geekbrains.javacommand.command.dtos.*;
import ru.geekbrains.javacommand.command.entities.Errand;
import ru.geekbrains.javacommand.command.entities.ErrandDetails;
import ru.geekbrains.javacommand.command.entities.ErrandStatusType;
import ru.geekbrains.javacommand.command.services.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ErrandController implements ErrandControllerApi {
    private final ErrandMatterTypeService matterTypeService;
    private final PlaceService placeService;
    private final PlaceTypeService placeTypeService;
    private final ErrandService errandService;
    private final ErrandDetailsService errandDetailsService;
    private final ErrandStatusTypeService errandStatusTypeService;

    @Override
    public List<ErrandMatterDto> getMatters() {
        return matterTypeService.findAll();
    }

    @Override
    public List<PlaceTypeDto> getPlaceTypes() {
        return placeTypeService.findAll();
    }

    @Override
    public List<PlaceDto> getPlaces(Long placeTypeId) {
        return placeService.findAllByPlaceTypeId(placeTypeId);
    }

    @Override
    public ErrandDto createErrand(Errand errand, ErrandDetails errandDetails) {
        errand.setId(null);
        errandDetails.setId(null);
        if (errand.getEmployee().getId() == 1){// начальник
            errand.setStatusType(errandStatusTypeService.returnFromDto(errandStatusTypeService.findById(2L)));
            errandDetails.setConfirmedOrRejectedBy(errand.getEmployee());
        return errandService.saveOrUpdate(errand);
        }
        errandDetailsService.save(errandDetails);
        errand.setStatusType(errandStatusTypeService.returnFromDto(errandStatusTypeService.findById(1L))); // остальные сотрудники
        return errandService.saveOrUpdate(errand);
    }

}
