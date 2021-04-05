package ru.geekbrains.javacommand.command.controllers.facade;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.javacommand.command.dtos.ErrandDto;
import ru.geekbrains.javacommand.command.dtos.ErrandMatterDto;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

	/**
	 * Update entity Errand by id
	 * @param errandUpdateDtoList
	 * @return ResponseEntity
	 */
	@PutMapping(path = "/update",
			consumes = "application/json;charset=UTF-8",
			produces = "application/json;charset=UTF-8")
	ResponseEntity<?> update(@RequestBody List<ErrandDto> errandUpdateDtoList);

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

}
