/*
 * License Headers.
 */

package ru.geekbrains.javacommand.command.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.javacommand.command.controllers.facade.ErrandControllerApi;
import ru.geekbrains.javacommand.command.dtos.ErrandUpdateDto;
import ru.geekbrains.javacommand.command.dtos.ErrandMatterDto;
import ru.geekbrains.javacommand.command.services.ErrandMatterTypeService;
import ru.geekbrains.javacommand.command.services.ErrandService;

import java.util.List;
import org.springframework.http.ResponseEntity;
import ru.geekbrains.javacommand.command.dtos.ErrandCreateDto;

@RestController
@RequiredArgsConstructor
public class ErrandController implements ErrandControllerApi {
	
	@Autowired
  private final ErrandMatterTypeService matterTypeService;
	@Autowired
	private final ErrandService errandService;

	@Override
	public ResponseEntity<?> finbById(Long id) {
    return ResponseEntity.ok(errandService.findErrandById(id));
	}

	@Override
	public ResponseEntity<?> create(List<ErrandCreateDto> errandCreateDtoList) {
    return ResponseEntity.ok(errandService.createErrand(errandCreateDtoList));
	}

	@Override
	public ResponseEntity<?> update(List<ErrandUpdateDto> errandUpdateDtoList) {
		return ResponseEntity.ok(errandService.updateErrand(errandUpdateDtoList));
	}

	@Override
	public ResponseEntity<?> deleteByIds(List<Long> idsList) {
		return ResponseEntity.ok(errandService.deleteErrand(idsList));
	}

	@Override
	public ResponseEntity<?> removeByIds(List<Long> idsList) {
		return ResponseEntity.ok(errandService.removeErrand(idsList));
	}
	
	@Override
	public List<ErrandMatterDto> getMatters() {
		return matterTypeService.findAll();
	}

}
