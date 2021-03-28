/*
 * License Headers.
 */
package ru.geekbrains.javacommand.command.services;

import ru.geekbrains.javacommand.command.dtos.CurrentErrandDto;

import java.util.List;

import ru.geekbrains.javacommand.command.dtos.ErrandDto;

public interface ErrandService {

	/**
	 *
	 * @param id
	 * @return ErrandResponseDto
	 */
	ErrandDto findErrandById(Long id);

	List<CurrentErrandDto> getListCurrent();

}
