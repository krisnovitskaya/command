/*
 * License Headers.
 */
package ru.geekbrains.javacommand.command.services;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.javacommand.command.dtos.CurrentErrandDto;

import java.util.List;

import ru.geekbrains.javacommand.command.dtos.ErrandDto;
import ru.geekbrains.javacommand.command.entities.Errand;
import ru.geekbrains.javacommand.command.util.PageImpl;

public interface ErrandService {

	/**
	 *
	 * @param id
	 * @return ErrandResponseDto
	 */
	ErrandDto findErrandById(Long id);

	PageImpl<ErrandDto> findAll(Specification<Errand> spec, int page, int size);

	List<CurrentErrandDto> getListCurrent();

}
