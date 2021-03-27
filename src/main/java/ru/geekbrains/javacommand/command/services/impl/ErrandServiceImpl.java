/*
 * License Headers.
 */
package ru.geekbrains.javacommand.command.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.dtos.ErrandResponseDto;
import ru.geekbrains.javacommand.command.entities.Errand;
import ru.geekbrains.javacommand.command.repositories.ErrandRepository;
import ru.geekbrains.javacommand.command.services.ErrandService;

/** @author Igor Popovich, email: popovichia@gmail.com */
@Service
@RequiredArgsConstructor
public class ErrandServiceImpl implements ErrandService {
	
	@Autowired
	private ErrandRepository errandRepository;
	
	@Override
	public ErrandResponseDto findErrandById(Long id) {
    
		return convertToErrandResponseDto(errandRepository.findErrandById(id));
		
	}
	
	private ErrandResponseDto convertToErrandResponseDto(Errand errand) {
		
		ErrandResponseDto resultErrandResponseDto = null;
		if (errand != null) {
//			resultErrandResponseDto = new ErrandResponseDto(
//					errand.getId(),
//					errand.getStatusType().getStatus(),
//					errand.getEmployee().getName(),
//					errand.getEmployee().getMiddleName(),
//					errand.getEmployee().getSurname(),
//					errand.getEmployee().getPosition().getPosition(),
//					errand.getEmployee().getDepartment().getTitle(),
//					errand.getEmployee().getDepartment().getMaster().,					
//					errand.getEmployee().getName(),
//			);
		}
		return resultErrandResponseDto;

	}
	
}
