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
import ru.geekbrains.javacommand.command.dtos.PlaceDto;
import ru.geekbrains.javacommand.command.dtos.PlaceTypeDto;
import ru.geekbrains.javacommand.command.entities.Errand;
import ru.geekbrains.javacommand.command.entities.ErrandDetails;
import ru.geekbrains.javacommand.command.services.*;

import java.util.List;
import org.springframework.http.ResponseEntity;

@RestController
@RequiredArgsConstructor
public class ErrandController implements ErrandControllerApi {
	private final ErrandMatterTypeService matterTypeService;
	private final PlaceService placeService;
	private final PlaceTypeService placeTypeService;
	private final ErrandService errandService;
	private final ErrandDetailsService errandDetailsService;
	private final ErrandStatusTypeService errandStatusTypeService;

	@Override
	public ResponseEntity<?> finbById(Long id) {
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

	@Override
	public List<PlaceTypeDto> getPlaceTypes() {
		return placeTypeService.findAll();
	}

	@Override
	public List<PlaceDto> getPlaces(Long placeTypeId) {
		return placeService.findAllByPlaceTypeId(placeTypeId);
	}

	@Override
	public ErrandDto createErrand(Errand errand, ErrandDetails errandDetails) {
		errand.setId(null);
		errandDetails.setId(null);
		if (errand.getEmployee().getId() == 1){// начальник
			errand.setStatusType(errandStatusTypeService.returnFromDto(errandStatusTypeService.findById(2L)));
			errandDetails.setConfirmedOrRejectedBy(errand.getEmployee());
			return null;//errandService.saveErrand(errand);
		}
		errandDetailsService.save(errandDetails);
		errand.setStatusType(errandStatusTypeService.returnFromDto(errandStatusTypeService.findById(1L))); // остальные сотрудники
		return null;//errandService.saveErrand(errand);
	}

}
