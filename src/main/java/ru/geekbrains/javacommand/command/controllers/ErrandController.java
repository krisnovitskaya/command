/*
 * License Headers.
 */

package ru.geekbrains.javacommand.command.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.javacommand.command.controllers.facade.ErrandControllerApi;
import ru.geekbrains.javacommand.command.dtos.ErrandResponseDto;
import ru.geekbrains.javacommand.command.services.impl.ErrandServiceImpl;

/** @author Igor Popovich, email: popovichia@gmail.com */
@RestController
@RequiredArgsConstructor
public class ErrandController implements ErrandControllerApi {

	@Autowired
	private ErrandServiceImpl errandServiceImpl;
	
	@Override
	public ErrandResponseDto finbById(Long id) {
		
    return errandServiceImpl.findErrandById(id);
	
	}
	
}
