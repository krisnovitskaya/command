/*
 * License Headers.
 */
package ru.geekbrains.javacommand.command.controllers.facade;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.javacommand.command.dtos.ErrandResponseDto;

/** @author Igor Popovich, email: popovichia@gmail.com */
@RequestMapping("/api/v1/errands")
public interface ErrandControllerApi {
	
	/**
	 *
	 * @param id
	 * @return ErrandResponseDto
	 */
	@GetMapping(path = "/findbyid", produces = "application/json;charset=UTF-8")
	ErrandResponseDto finbById(@RequestParam (name = "id") Long id);
	
}
