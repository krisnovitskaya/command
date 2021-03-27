/*
 * License Headers.
 */
package ru.geekbrains.javacommand.command.services;

import ru.geekbrains.javacommand.command.dtos.ErrandResponseDto;

/** @author Igor Popovich, email: popovichia@gmail.com */
public interface ErrandService {

	/**
	 *
	 * @param id
	 * @return ErrandResponseDto
	 */
	ErrandResponseDto findErrandById(Long id);

}
