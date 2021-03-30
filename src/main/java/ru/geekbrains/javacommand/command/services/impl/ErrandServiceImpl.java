/*
 * License Headers.
 */
package ru.geekbrains.javacommand.command.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.dtos.ErrandDto;
import ru.geekbrains.javacommand.command.entities.Errand;
import ru.geekbrains.javacommand.command.repositories.ErrandRepository;
import ru.geekbrains.javacommand.command.services.ErrandService;
import ru.geekbrains.javacommand.command.dtos.CurrentErrandDto;
import ru.geekbrains.javacommand.command.util.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ErrandServiceImpl implements ErrandService {
	
	@Autowired
	private final ErrandRepository errandRepository;

	@Override
	public List<CurrentErrandDto> getListCurrent() {
		return errandRepository.findCurrent().stream().map(CurrentErrandDto::new).collect(Collectors.toList());
	}
	
	@Override
	public ErrandDto findErrandById(Long id) {
    
		return convertToErrandDto(errandRepository.findErrandById(id));
		
	}

	@Override
	public PageImpl<ErrandDto> findAll(Specification<Errand> spec, int page, int size) {
		Page<Errand> errandPage = errandRepository.findAll(spec, PageRequest.of(page, size));

		PageImpl<ErrandDto> errandDtoPage = new PageImpl<>();
		errandDtoPage.setContent(errandPage.getContent().stream().map(this::convertToErrandDto).collect(Collectors.toList()));
		errandDtoPage.setNumber(errandPage.getNumber());
		errandDtoPage.setSize(errandPage.getSize());
		errandDtoPage.setTotalPages(errandPage.getTotalPages());
		errandDtoPage.setTotalElements(errandPage.getTotalElements());

		return errandDtoPage;
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
