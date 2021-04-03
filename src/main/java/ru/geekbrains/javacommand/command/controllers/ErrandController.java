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
import org.springframework.http.ResponseEntity;

@RestController
@RequiredArgsConstructor
public class ErrandController implements ErrandControllerApi {
	
	@Autowired
  private final ErrandMatterTypeService matterTypeService;
	@Autowired
	private final ErrandService errandService;

	@Override
	public ResponseEntity<?> findById(Long id) {
    return ResponseEntity.ok(errandService.findErrandById(id));
	}

	@Override
	public ResponseEntity<?> create(ErrandDto errandDto) {
    return ResponseEntity.ok(errandService.saveErrand(errandDto));
	}

	@Override
	public ResponseEntity<?> updateById(Long id, ErrandDto errandDto) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public ResponseEntity<?> deleteById(Long id) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public ResponseEntity<?> removeById(Long id) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
	@Override
	public List<ErrandMatterDto> getMatters() {
		return matterTypeService.findAll();
	}

}
