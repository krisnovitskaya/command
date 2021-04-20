/*
 * License Headers.
 */
package ru.geekbrains.javacommand.command.controllers.facade;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.javacommand.command.dtos.*;
import ru.geekbrains.javacommand.command.util.PageImpl;

@RequestMapping("/api/v1/errands")
public interface ErrandControllerApi {

  @GetMapping(value = "/statistic", produces = "application/json;charset=UTF-8")
  List<ErrandStatisticDto> getStatistic(@RequestParam MultiValueMap<String, String> params,
                               Principal principal);

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
      @RequestParam MultiValueMap<String, String> params,
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
   * Update entity Errand by id
   *
   * @param errandUpdateDtoList
   * @return ResponseEntity
   */
  @PutMapping(
      path = "/update",
      consumes = "application/json;charset=UTF-8",
      produces = "application/json;charset=UTF-8")
  ResponseEntity<?> update(@RequestBody List<ErrandDto> errandUpdateDtoList);

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

  /** @return */
  @GetMapping(value = "/place_types", produces = "application/json;charset=UTF-8")
  List<PlaceTypeDto> getPlaceTypes();

  /**
   * @param placeTypeId
   * @return
   */
  @GetMapping(value = "/places/{placeType_id}", produces = "application/json;charset=UTF-8")
  List<PlaceDto> getPlaces(@PathVariable(name = "placeType_id") Long placeTypeId);

  /**
   * @param params
   * @return report file
   */
  @GetMapping(value = "/report")
  ResponseEntity<?> getReportFile(@RequestParam MultiValueMap<String, String> params);

  @PostMapping("/create_errand")
  public void createNewErrand(@RequestBody CreatedErrandDto errandDto, Principal principal);
}