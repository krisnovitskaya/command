package ru.geekbrains.javacommand.command.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.javacommand.command.controllers.facade.ErrandsStatisticsControllerApi;
import ru.geekbrains.javacommand.command.dtos.ErrandDto;
import ru.geekbrains.javacommand.command.dtos.PlaceDto;
import ru.geekbrains.javacommand.command.entities.Errand;
import ru.geekbrains.javacommand.command.entities.ErrandStatusType;
import ru.geekbrains.javacommand.command.entities.PlaceType;
import ru.geekbrains.javacommand.command.services.ErrandService;
import ru.geekbrains.javacommand.command.services.ErrandStatusTypeService;
import ru.geekbrains.javacommand.command.services.PlaceService;
import ru.geekbrains.javacommand.command.services.PlaceTypeService;
import ru.geekbrains.javacommand.command.utils.ErrandSpecificationResolver;

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
        var resolvedSpecs = new ErrandSpecificationResolver(specs).resolve();
        Page<Errand> errands = errandService.getAllErrands(resolvedSpecs, page < 0 ? 1 : page - 1, 3);
        var result = new PageImpl<>(errands.stream().map(ErrandDto::new).collect(Collectors.toList()));
        return ResponseEntity.ok(result);
    }

    @Override
    public List<String> getStatuses() {
        return convertToDtosList(ErrandStatusType::getStatus, statusTypeService.getAll());
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
