/*
 * License Headers.
 */
package ru.geekbrains.javacommand.command.services;

import ru.geekbrains.javacommand.command.dtos.CurrentErrandDto;

import java.util.List;

import ru.geekbrains.javacommand.command.dtos.ErrandDto;
import ru.geekbrains.javacommand.command.entities.Errand;

public interface ErrandService {

	/**
	 *
	 * @param id
	 * @return ErrandDto
	 */
	ErrandDto findErrandById(Long id);

	void saveErrand(Errand errand);
	
	List<CurrentErrandDto> getListCurrent();

	ErrandDto saveOrUpdate(Errand errand);

}
