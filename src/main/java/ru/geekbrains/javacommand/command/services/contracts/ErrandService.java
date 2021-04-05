/*
 * License Headers.
 */
package ru.geekbrains.javacommand.command.services.contracts;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.javacommand.command.dtos.CurrentErrandDto;

import java.io.ByteArrayInputStream;
import java.util.List;

import ru.geekbrains.javacommand.command.dtos.ErrandDto;
import ru.geekbrains.javacommand.command.entities.Errand;
import ru.geekbrains.javacommand.command.util.PageImpl;

public interface ErrandService {

	/**
	 *
	 * @param id
	 * @return ErrandDto
	 */
	ErrandDto findErrandById(Long id);

	PageImpl<ErrandDto> findAll(Specification<Errand> spec, int page, int size);

	Errand saveErrand(ErrandDto errandDto);

	List<CurrentErrandDto> getListCurrent();

	ByteArrayInputStream findAllForReport(Specification<Errand> spec);

}