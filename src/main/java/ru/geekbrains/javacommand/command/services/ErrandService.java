/*
 * License Headers.
 */
package ru.geekbrains.javacommand.command.services;

import ru.geekbrains.javacommand.command.dtos.ErrandDto;

/** @author Igor Popovich, email: popovichia@gmail.com */
public interface ErrandService {

	/**
	 *
	 * @param id
	 * @return ErrandResponseDto
	 */
	ErrandDto findErrandById(Long id);

}
