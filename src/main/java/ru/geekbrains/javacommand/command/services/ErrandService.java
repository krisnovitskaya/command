/*
 * License Headers.
 */
package ru.geekbrains.javacommand.command.services;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
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

	Errand saveErrand(ErrandDto errandDto);
	
	List<CurrentErrandDto> getListCurrent();

	Page<Errand> getAllErrands(Specification<Errand> specification, int page, int size);
}
