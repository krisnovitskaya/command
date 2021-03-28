/*
 * License Headers.
 */

package ru.geekbrains.javacommand.command.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.javacommand.command.controllers.facade.ErrandControllerApi;
import ru.geekbrains.javacommand.command.dtos.ErrandDto;
import ru.geekbrains.javacommand.command.dtos.ErrandMatterDto;
import ru.geekbrains.javacommand.command.services.impl.ErrandServiceImpl;
import ru.geekbrains.javacommand.command.services.ErrandMatterTypeService;

import java.util.List;

/** @author Igor Popovich, email: popovichia@gmail.com */
@RestController
@RequiredArgsConstructor
public class ErrandController implements ErrandControllerApi {
	@Autowired
	private ErrandServiceImpl errandServiceImpl;
	
	@Autowired
	private final ErrandMatterTypeService matterTypeService;
	
	@Override
	public ErrandDto finbById(Long id) {
    return errandServiceImpl.findErrandById(id);
	}
	
	@Override
	public List<ErrandMatterDto> getMatters() {
		return matterTypeService.findAll();
	}

}
