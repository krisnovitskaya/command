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
import ru.geekbrains.javacommand.command.services.ErrandMatterTypeService;
import ru.geekbrains.javacommand.command.services.ErrandService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ErrandController implements ErrandControllerApi {
	
	@Override
	public ErrandDto finbById(Long id) {
    return errandService.findErrandById(id);
	}
	
	@Override
	public List<ErrandMatterDto> getMatters() {
		return matterTypeService.findAll();
	}

	@Autowired
  private final ErrandMatterTypeService matterTypeService;
	@Autowired
	private final ErrandService errandService;

}
