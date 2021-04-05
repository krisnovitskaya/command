package ru.geekbrains.javacommand.command.services.impl;

import java.io.ByteArrayInputStream;
import java.time.OffsetDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.dtos.ErrandDto;
import ru.geekbrains.javacommand.command.entities.Errand;
import ru.geekbrains.javacommand.command.repositories.ErrandRepository;
import ru.geekbrains.javacommand.command.services.contracts.ErrandService;
import ru.geekbrains.javacommand.command.dtos.CurrentErrandDto;
import ru.geekbrains.javacommand.command.util.PageImpl;
import ru.geekbrains.javacommand.command.util.ReportErrandExporterExcel;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ErrandServiceImpl implements ErrandService {

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
	public Errand saveErrand(ErrandDto errandDto) {
		
		Errand errand = convertToErrand(errandDto);
		return errandRepository.save(errand);
		
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

	@Override
	public ByteArrayInputStream findAllForReport(Specification<Errand> spec){
		return ReportErrandExporterExcel.errandsToExcel(errandRepository.findAll(spec));
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

	private Errand convertToErrand(ErrandDto errandDto) {
		
		Errand errand = new Errand();
    errand.setDateStart(OffsetDateTime.now().plusDays(1));
    errand.setDateEnd(OffsetDateTime.now().plusWeeks(1));
		return errand;
		
	}
	
}
