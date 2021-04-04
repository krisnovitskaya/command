/*
 * License Headers.
 */
package ru.geekbrains.javacommand.command.services;

import ru.geekbrains.javacommand.command.dtos.CurrentErrandDto;

import java.util.List;
import ru.geekbrains.javacommand.command.dtos.ErrandAboutInfoDto;
import ru.geekbrains.javacommand.command.dtos.ErrandCreateDto;
import ru.geekbrains.javacommand.command.dtos.ErrandDeleteDto;
import ru.geekbrains.javacommand.command.dtos.ErrandRemoveDto;
import ru.geekbrains.javacommand.command.dtos.ErrandUpdateDto;

/**
 *
 * @author
 */
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
	List<ErrandUpdateDto> createErrand(List<ErrandCreateDto> errandCreateDtoList);
	
	/**
	 *
	 * @param errandUpdateDtoList
	 * @return List ErrandUpdateDto
	 */
	List<ErrandUpdateDto> updateErrand(List<ErrandUpdateDto> errandUpdateDtoList);

	/**
	 *
	 * @param idsList
	 * @return
	 */
	ErrandDeleteDto deleteErrand(List<Long> idsList);
	
	/**
	 *
	 * @param idsList
	 * @return
	 */
	ErrandRemoveDto removeErrand(List<Long> idsList);

	/**
	 *
	 * @return List CurrentErrandDto
	 */
	List<CurrentErrandDto> getListCurrent();

}
