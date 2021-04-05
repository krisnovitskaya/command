/*
 * License Headers.
 */
package ru.geekbrains.javacommand.command.services;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.javacommand.command.dtos.CurrentErrandDto;

import java.util.List;
import ru.geekbrains.javacommand.command.dtos.ErrandAboutInfoDto;
import ru.geekbrains.javacommand.command.dtos.ErrandCreateDto;
import ru.geekbrains.javacommand.command.dtos.ErrandDeleteDto;
import ru.geekbrains.javacommand.command.dtos.ErrandRemoveDto;
import ru.geekbrains.javacommand.command.dtos.ErrandUpdateDto;
import ru.geekbrains.javacommand.command.dtos.ErrandDto;
import ru.geekbrains.javacommand.command.entities.Errand;
import ru.geekbrains.javacommand.command.util.PageImpl;

public interface ErrandService {

	/**
	 *
	 * @param id
	 * @return ErrandAboutInfoDto
	 */
	ErrandAboutInfoDto findErrandById(Long id);

	/**
	 *
	 * @param errandCreateDtoList
	 * @return List ErrandCreateDto
	 */
	List<ErrandUpdateDto> createErrands(List<ErrandCreateDto> errandCreateDtoList);
	
	/**
	 *
	 * @param errandUpdateDtoList
	 * @return List ErrandUpdateDto
	 */
	List<ErrandUpdateDto> updateErrands(List<ErrandUpdateDto> errandUpdateDtoList);

	/**
	 *
	 * @param idsList
	 * @return List ErrandDeleteDto
	 */
	List<ErrandDeleteDto> deleteErrands(List<Long> idsList);
	
	/**
	 *
	 * @param idsList
	 * @return List 
	 */
	List<ErrandRemoveDto> removeErrands(List<Long> idsList);

	/**
	 *
	 * @return List CurrentErrandDto
	 */
	PageImpl<ErrandDto> findAll(Specification<Errand> spec, int page, int size);

	Errand saveErrand(ErrandDto errandDto);

	List<CurrentErrandDto> getListCurrent();

}
