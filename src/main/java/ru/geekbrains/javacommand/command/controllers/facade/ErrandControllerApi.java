package ru.geekbrains.javacommand.command.controllers.facade;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.javacommand.command.dtos.*;

@RequestMapping("/api/v1/errands")
public interface ErrandControllerApi {

    /**
     * Find all pending errands by Master via Principal
     *
     * @param page
     * @param params
     * @return PageImpl<ErrandDto>
     */
    @GetMapping(value = "/pending", produces = "application/json")
    ResponseEntity<?> getAllErrands(
            @RequestParam(defaultValue = "1", name = "p") Integer page,
            @RequestParam Map<String, String> params,
            Principal principal);

    /**
     * Find entity Errand by id
     *
     * @param id
     * @return ErrandDto
     */
    @GetMapping(path = "/findbyid", produces = "application/json;charset=UTF-8")
    ResponseEntity<?> findById(@RequestParam(name = "id") Long id);

    /**
     * Find ErrandDto by id
     *
     * @param errandId
     * @return ErrandDto
     */
    @PostMapping(path = "/get_details", produces = "application/json;charset=UTF-8")
    ResponseEntity<?> getErrandDetails(@RequestParam(name = "errandId") Long errandId);

    /**
     * @param errandDto
     * @return
     */
    @PostMapping(
            path = "/create",
            consumes = "application/json;charset=UTF-8",
            produces = "application/json;charset=UTF-8")
    ResponseEntity<?> create(@RequestBody ErrandDto errandDto);

    /**
     * Create entity Errand
     *
     * @param errandCreateDtoList
     * @return ResponseEntity
     */
    @PostMapping(
            path = "/createlist",
            consumes = "application/json;charset=UTF-8",
            produces = "application/json;charset=UTF-8")
    ResponseEntity<?> createList(@RequestBody List<ErrandDto> errandCreateDtoList);

    /**
     * @param newErrandDto
     * @param principal
     */
    @PostMapping(
            path = "/createerrand",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    void createErrand(ErrandDto newErrandDto, Principal principal);

    /**
     * @param newErrandDto
     */
    @PostMapping("/createerrandnew")
    void createErrandNew(@RequestBody NewErrandDto newErrandDto);

    /**
     * Update Errand entity
     *
     * @param errandDto
     * @return ResponseEntity
     */
    @PostMapping(
            path = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> updateErrand(@RequestBody ErrandDto errandDto);

    /**
     * Update entity Errand by id
     *
     * @param id
     * @param errandDto
     * @return ResponseEntity<?>
     */
    @PutMapping(
            path = "/updatebyid",
            consumes = "application/json;charset=UTF-8",
            produces = "application/json;charset=UTF-8")
    ResponseEntity<?> updateById(
            @RequestParam(name = "id") Long id, @RequestBody ErrandDto errandDto);

    /**
     * Update Errand status by id
     *
     * @param errandId
     * @param status
     * @return ResponseEntity<?>
     */
    @PostMapping(
            path = "/updateStatus")
    ResponseEntity<?> updateErrandStatus(@RequestParam Long errandId, @RequestParam String status);

    /**
     * Fill deleted field to true for Errand entity by id
     *
     * @param id
     * @return ResponseEntity<?>
     */
    @DeleteMapping(path = "/deletebyid", produces = "application/json;charset=UTF-8")
    ResponseEntity<?> deleteById(@RequestParam(name = "id") Long id);

    /**
     * Fill deleted field to true for Errand entity by id
     *
     * @param idsList
     * @return ResponseEntity
     */
    @DeleteMapping(path = "/deletebyids", produces = "application/json;charset=UTF-8")
    ResponseEntity<?> deleteByIds(@RequestBody List<Long> idsList);

    /**
     * Remove entity Errand by id from DataBase
     *
     * @param id
     * @return ResponseEntity<?>
     */
    @DeleteMapping(path = "/removebyid", produces = "application/json;charset=UTF-8")
    ResponseEntity<?> removeById(@RequestParam(name = "id") Long id);

    /**
     * Remove entity Errand by id from DataBase
     *
     * @param idsList
     * @return ResponseEntity
     */
    @DeleteMapping(path = "/removebyids", produces = "application/json;charset=UTF-8")
    ResponseEntity<?> removeByIds(@RequestBody List<Long> idsList);

    /**
     * Return All ErrandMatterType as DTO from current DataBase
     *
     * @return List<ErrandMatterDto>
     */
    @GetMapping(value = "/matters", produces = "application/json;charset=UTF-8")
    List<ErrandMatterDto> getMatters();

    /**
     * Return All ErrandStatusType as List DTO from current DataBase
     *
     * @return List<ErrandStatusTypeDto>
     */
    @GetMapping(value = "/statuses", produces = "application/json;charset=UTF-8")
    List<ErrandStatusTypeDto> getStatuses();

    /**
     * @return
     */
    @GetMapping("/get_place_types_list")
    List<PlaceTypeDto> getPlaceTypes();

    /**
     * @param placeTypeId
     * @return
     */
    @GetMapping("/get_places_list/{placeType_id}")
    List<PlaceDto> getPlaces(@PathVariable(name = "placeType_id") Long placeTypeId);

    /**
     * @param params
     * @return report file
     */
    @GetMapping(value = "/report")
    ResponseEntity<?> getReportFile(@RequestParam Map<String, String> params);

    @PostMapping("/create_errand")
    void createNewErrand(@RequestBody CreatedErrandDto errandDto, Principal principal);
}