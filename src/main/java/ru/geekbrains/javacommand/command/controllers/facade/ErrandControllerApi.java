package ru.geekbrains.javacommand.command.controllers.facade;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.javacommand.command.dtos.ErrandDto;
import ru.geekbrains.javacommand.command.dtos.ErrandMatterDto;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import ru.geekbrains.javacommand.command.dtos.PlaceDto;
import ru.geekbrains.javacommand.command.dtos.PlaceTypeDto;
import ru.geekbrains.javacommand.command.entities.Errand;

@RequestMapping("/api/v1/errands")
public interface ErrandControllerApi {

	/**
	 * Find entity Errand by id
	 * @param id
	 * @return ResponseEntity<?>
	 */
	@GetMapping(path = "/findbyid",
			produces = "application/json;charset=UTF-8")
	ResponseEntity<?> findById(@RequestParam (name = "id") Long id);

	/**
	 * Create entity Errand
	 * @param errandCreateDtoList
	 * @return ResponseEntity
	 */
  	@PostMapping(
      path = "/create",
      consumes = "application/json;charset=UTF-8",
      produces = "application/json;charset=UTF-8")
	ResponseEntity<?> create(@RequestBody List<ErrandDto> errandCreateDtoList);

	@PostMapping(
			path = "/create_errand",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	void createErrand(ErrandDto newErrandDto, Principal principal);

	/**
	 * Update entity Errand by id
	 * @param errandUpdateDtoList
	 * @return ResponseEntity
	 */
	@PutMapping(path = "/update",
			consumes = "application/json;charset=UTF-8",
			produces = "application/json;charset=UTF-8")
	ResponseEntity<?> update(@RequestBody List<ErrandDto> errandUpdateDtoList);

	@GetMapping(path = "/updateStatus",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> updateErrandStatus(@RequestParam Long errandId, @RequestParam String status);

	/**
	 * Fill deleted field to true for Errand entity by id
	 * @param idsList
	 * @return ResponseEntity
	 */
	@DeleteMapping(path = "/deletebyids",
			produces = "application/json;charset=UTF-8")
	ResponseEntity<?> deleteByIds(@RequestBody List<Long> idsList);

	/**
	 * Remove entity Errand by id from DataBase
	 * @param idsList
	 * @return ResponseEntity
	 */
	@DeleteMapping(path = "/removebyids",
			produces = "application/json;charset=UTF-8")
	ResponseEntity<?> removeByIds(@RequestBody List<Long> idsList);

	/**
	 * Return All ErrandMatterType as DTO from current DataBase
	 * @return List ErrandMatterDto
	 */
	@GetMapping(value = "/matters", produces = "application/json;charset=UTF-8")
	List<ErrandMatterDto> getMatters();

	/**
	 *
	 * @param params
	 * @return report file
	 */
	@GetMapping(value = "/report")
	ResponseEntity<?> getReportFile(@RequestParam Map<String, String> params);

	@GetMapping("/getPlaceTypesList")
	List<PlaceTypeDto> getPlaceTypes();

	@GetMapping("/getPlacesList/{id}")
	List<PlaceDto> getPlaces(@PathVariable Long id);

	@PostMapping("/createErrand")
	public void createNewErrand(@RequestBody ErrandDto errandDto, Principal principal);

}
