/*
 * License Headers.
 */
package ru.geekbrains.javacommand.command.controllers.facade;

import org.springframework.web.bind.annotation.*;
import ru.geekbrains.javacommand.command.dtos.*;

import java.security.Principal;
import java.util.List;
import org.springframework.http.ResponseEntity;
import ru.geekbrains.javacommand.command.entities.Employee;
import ru.geekbrains.javacommand.command.entities.Errand;
import ru.geekbrains.javacommand.command.entities.ErrandDetails;

@RequestMapping("/api/v1/errands")
public interface ErrandControllerApi {
	
	/**
	 * Find entity Errand by id
	 * @param id
	 * @return ErrandDto
	 */
	@GetMapping(path = "/findbyid",
			produces = "application/json;charset=UTF-8")
	ResponseEntity<?> finbById(@RequestParam (name = "id") Long id);

	/**
	 * Create entity Errand
	 * @param errandDto
	 * @return ResponseEntity<?>
	 */
//  @PostMapping(
//      path = "/create",
//      consumes = "application/json;charset=UTF-8",
//      produces = "application/json;charset=UTF-8")
//  ResponseEntity<?> create(@RequestBody ErrandDto errandDto);

	/**
	 * Update entity Errand by id
	 * @param id
	 * @param errandDto
	 * @return ResponseEntity<?>
	 */
	@PutMapping(path = "/updatebyid",
			consumes = "application/json;charset=UTF-8",
			produces = "application/json;charset=UTF-8")
	ResponseEntity<?> updateById(@RequestParam (name = "id") Long id, @RequestBody ErrandDto errandDto);

	/**
	 * Fill deleted field to true for Errand entity by id
	 * @param id
	 * @return ResponseEntity<?>
	 */
	@DeleteMapping(path = "/deletebyid",
			produces = "application/json;charset=UTF-8")
	ResponseEntity<?> deleteById(@RequestParam (name = "id") Long id);

	/**
	 * Remove entity Errand by id from DataBase
	 * @param id
	 * @return ResponseEntity<?>
	 */
	@DeleteMapping(path = "/removebyid",
			produces = "application/json;charset=UTF-8")
	ResponseEntity<?> removeById(@RequestParam (name = "id") Long id);

	/**
	 * Return All ErrandMatterType as DTO from current DataBase
	 * @return List<ErrandMatterDto>
	 */
	@GetMapping(value = "/matters", produces = "application/json;charset=UTF-8")
	List<ErrandMatterDto> getMatters();

	@GetMapping("/getPlaceTypesList")
	List<PlaceTypeDto> getPlaceTypes();

	@GetMapping("/getPlacesList/{placeType_id}")
	List<PlaceDto> getPlaces(@PathVariable(name = "placeType_id") Long placeTypeId);

	@PostMapping("/createErrand")
	public void createErrand(@RequestBody NewErrandDto newErrandDto);

}
