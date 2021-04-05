package ru.geekbrains.javacommand.command.controllers.facade;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.javacommand.command.dtos.PlaceDto;

import java.util.List;
import java.util.Map;

/**
 * @author owpk
 */
@RequestMapping("/api/v1/statistics")
public interface ErrandsStatisticsControllerApi {

    ResponseEntity<?> getAllErrands(Integer page, Map<String, String> specs);

    /**
     * A String equivalent of APPLICATION_JSON_UTF8.
     * Deprecated
     * as of 5.2 in favor of APPLICATION_JSON_VALUE since major browsers like Chrome now comply
     * with the specification  and interpret correctly UTF-8 special characters
     * without requiring a charset=UTF-8 parameter.
     */
    @GetMapping(value = "/statuses", produces = MediaType.APPLICATION_JSON_VALUE)
    List<String> getStatuses();
    @GetMapping(value = "/places", produces = MediaType.APPLICATION_JSON_VALUE)
    List<PlaceDto> getPlaces();
    @GetMapping(value = "/place_types", produces = MediaType.APPLICATION_JSON_VALUE)
    List<String> getPlaceTypes();
}
