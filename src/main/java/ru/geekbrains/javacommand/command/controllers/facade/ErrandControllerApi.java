/*
 * License Headers.
 */
package ru.geekbrains.javacommand.command.controllers.facade;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.javacommand.command.dtos.ErrandDto;
import ru.geekbrains.javacommand.command.dtos.ErrandMatterDto;

import java.util.List;

@RequestMapping("/api/v1/errands")
public interface ErrandControllerApi {
	
	/**
	 *
	 * @param id
	 * @return ErrandResponseDto
	 */
	@GetMapping(path = "/findbyid", produces = "application/json;charset=UTF-8")
	ErrandDto finbById(@RequestParam (name = "id") Long id);

	/**
	 * Return All ErrandMatterType as DTO from current DataBase
	 * @return List<ErrandMatterDto>
	 */
	@GetMapping(value = "/matters", produces = "application/json;charset=UTF-8")
	List<ErrandMatterDto> getMatters();

}
