/*
 * License Headers.
 */
package ru.geekbrains.javacommand.command.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.dtos.ErrandDto;
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
	public ErrandDto findErrandById(Long id) {
    
		return convertToErrandDto(errandRepository.findErrandById(id));
		
	}
	
	private ErrandDto convertToErrandDto(Errand errand) {
		
		ErrandDto resultErrandDto = null;
		if (errand != null) {
			resultErrandDto = new ErrandDto(
					errand.getId(),
					errand.getCreated(),
					errand.getUpdated(),
					errand.getStatusType().getStatus(),
					errand.getDateStart(),
					errand.getDateEnd(),
					errand.getEmployee().getFirstName(),
					errand.getEmployee().getMiddleName(),
					errand.getEmployee().getLastName(),
					errand.getEmployee().getPosition().getPosition(),
					errand.getEmployee().getUser().getUserName(),
					errand.getEmployee().getDepartment().getTitle(),
					errand.getEmployee().getDepartment().getMaster().getFirstName(),
					errand.getEmployee().getDepartment().getMaster().getMiddleName(),
					errand.getEmployee().getDepartment().getMaster().getLastName(),
					errand.getEmployee().getDepartment().getMaster().getUser().getUserName(),
					errand.getErrandDetails().getMatter().getMatter(),
					errand.getErrandDetails().getPlace().getTitle(),
					errand.getErrandDetails().getPlace().getPlaceType().getType(),
					errand.getErrandDetails().getComment(),
					errand.getErrandDetails().getCreatedBy().getFirstName(),
					errand.getErrandDetails().getCreatedBy().getMiddleName(),
					errand.getErrandDetails().getCreatedBy().getLastName(),
					errand.getErrandDetails().getConfirmedOrRejectedBy().getFirstName(),
					errand.getErrandDetails().getConfirmedOrRejectedBy().getMiddleName(),
					errand.getErrandDetails().getConfirmedOrRejectedBy().getLastName()
			);
		}
		return resultErrandDto;

	}
	
}
