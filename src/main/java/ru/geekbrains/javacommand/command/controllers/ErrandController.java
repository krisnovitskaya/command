/*
 * License Headers.
 */

package ru.geekbrains.javacommand.command.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.javacommand.command.controllers.facade.ErrandControllerApi;
import ru.geekbrains.javacommand.command.dtos.*;
import ru.geekbrains.javacommand.command.entities.*;
import ru.geekbrains.javacommand.command.services.*;

import java.security.Principal;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
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
	private final EmployeeService employeeService;

	@Override
	public ResponseEntity<?> finbById(Long id) {
    return ResponseEntity.ok(errandService.findErrandById(id));
	}
//
//	@Override
//	public ResponseEntity<?> create(ErrandDto errandDto) { return ResponseEntity.ok(errandService.saveOrUpdate(errandDto));
//	}

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
	public void createErrand(NewErrandDto newErrandDto) {
		ErrandMatterDto errandMatterTypeDto = matterTypeService.findById(newErrandDto.getMatterId());
		ErrandMatterType errandMatterType = matterTypeService.convertToErrandMatterType(errandMatterTypeDto);

		PlaceDto placeDto = placeService.findById(newErrandDto.getPlaceId());
		Place place = placeService.convertToPlace(placeDto);

		EmployeeDto employeeDto = employeeService.findById(newErrandDto.getEmployeeId());
		Employee employee = employeeService.convertToEmployee(employeeDto);

		ErrandDetails newErrandDetails = new ErrandDetails(errandMatterType, place, newErrandDto.getComment(), employee);
		newErrandDetails.setId(null);

		errandDetailsService.save(newErrandDetails);

		Errand newErrand = new Errand(employee, newErrandDetails, newErrandDto.getDateStart(), newErrandDto.getDateEnd());
		newErrand.setId(null);

		errandService.saveErrand(newErrand);

		newErrandDetails.setCreated(OffsetDateTime.now());
		newErrandDetails.setUpdated(OffsetDateTime.now());
		newErrand.setCreated(OffsetDateTime.from(ZonedDateTime.now()));
		newErrand.setUpdated(OffsetDateTime.from(ZonedDateTime.now()));

		if (newErrand.getEmployee().getId() == 1){// начальник
			newErrand.setStatusType(errandStatusTypeService.returnFromDto(errandStatusTypeService.findById(2L)));
			newErrandDetails.setConfirmedOrRejectedBy(newErrand.getEmployee());
		}
		else {
			newErrand.setStatusType(errandStatusTypeService.returnFromDto(errandStatusTypeService.findById(1L)));
		}// остальные сотрудники


		System.out.println(" getEmployee().getId()  " + newErrand.getEmployee().getId() +
				" newErrand.getErranddet().getId()  " + newErrand.getErrandDetails().getId() +
				" newErrand.getDateStart()  " + newErrand.getDateStart() +
				" newErrand.getstatus  " + newErrand.getStatusType() +
				" newErrand.getDateEnd()  " + newErrand.getDateEnd());
		System.out.println(" errandDetails.getMatter().getMatter()  " + newErrandDetails.getMatter().getMatter() +
				" newErrandDetails.getPlace() " + newErrandDetails.getPlace().getTitle() +
				"  newErrandDetails.getComment()  " + newErrandDetails.getComment() +
				"  newErrandDetails.getdate  " + newErrandDetails.getCreated()+
				"  newErrandDetails.cjnf  " + newErrandDetails.getConfirmedOrRejectedBy());
		
	}

}
