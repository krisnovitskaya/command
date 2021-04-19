package ru.geekbrains.javacommand.command.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.javacommand.command.controllers.facade.ErrandsStatisticsControllerApi;
import ru.geekbrains.javacommand.command.dtos.ErrandStatusTypeDto;
import ru.geekbrains.javacommand.command.dtos.PlaceDto;
import ru.geekbrains.javacommand.command.entities.ErrandStatusType;
import ru.geekbrains.javacommand.command.entities.PlaceType;
import ru.geekbrains.javacommand.command.services.contracts.ErrandService;
import ru.geekbrains.javacommand.command.services.contracts.ErrandStatusTypeService;
import ru.geekbrains.javacommand.command.services.contracts.PlaceService;
import ru.geekbrains.javacommand.command.services.contracts.PlaceTypeService;
import ru.geekbrains.javacommand.command.util.ErrandsStatisticSpecificationResolver;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author owpk
 */
@RequiredArgsConstructor
@RestController
public class ErrandsStatisticController implements ErrandsStatisticsControllerApi {
    private final ErrandService errandService;
    private final ErrandStatusTypeService statusTypeService;
    private final PlaceService placeService;
    private final PlaceTypeService placeTypeService;

    @Override
    public ResponseEntity<?> getAllErrands(Integer page, Map<String, String> specs) {
        var resolvedSpecs = new ErrandsStatisticSpecificationResolver(specs).resolve();
        var errands = errandService.findAll(resolvedSpecs, page < 0 ? 1 : page - 1, 3);
        return ResponseEntity.ok(errands);
    }

    @Override
    public List<String> getStatuses() {
//        return convertToDtosList(ErrandStatusType::getStatus, statusTypeService.findAll());
        return statusTypeService.findAll().stream().map(ErrandStatusTypeDto::getStatus).collect(Collectors.toList());
    }

    @Override
    public List<PlaceDto> getPlaces() {
        return convertToDtosList(PlaceDto::new, placeService.getAll());
    }

    @Override
    public List<String> getPlaceTypes() {
        return convertToDtosList(PlaceType::getType, placeTypeService.getAll());
    }

    private <P, O> List<P> convertToDtosList(Function<? super O, ? extends P> function,
                                             List<O> list) {
        return list.stream()
                .map(function)
                .collect(Collectors.toList());
    }
}
