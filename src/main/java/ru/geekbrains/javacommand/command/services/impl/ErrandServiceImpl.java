/*
 * License Headers.
 */
package ru.geekbrains.javacommand.command.services.impl;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.dtos.ErrandDto;
import ru.geekbrains.javacommand.command.entities.Employee;
import ru.geekbrains.javacommand.command.entities.Errand;
import ru.geekbrains.javacommand.command.entities.ErrandDetails;
import ru.geekbrains.javacommand.command.entities.ErrandStatusType;
import ru.geekbrains.javacommand.command.repositories.*;
import ru.geekbrains.javacommand.command.services.ErrandService;
import ru.geekbrains.javacommand.command.util.PageImpl;
import ru.geekbrains.javacommand.command.util.ReportErrandExporterExcel;

@Service
@RequiredArgsConstructor
public class ErrandServiceImpl implements ErrandService {

    private final ErrandRepository errandRepository;
    private final EmployeeRepository employeeRepository;
    private final ErrandStatusTypeRepository errandStatusTypeRepository;
    private final ErrandMatterTypeRepository errandMatterTypeRepository;
    private final PlaceRepository placeRepository;


//	@Override
//	public List<CurrentErrandDto> getListCurrent() {
//
//		return errandRepository.findCurrent().stream().map(CurrentErrandDto::new).collect(Collectors.toList());
//
//	}
    @Override
    public List<ErrandDto> getListCurrent() {
        return errandRepository.findCurrent().stream()
                .map(ErrandDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public ErrandDto findErrandById(Long id) {
        return new ErrandDto(errandRepository.findErrandById(id));
    }

	@Override
	public void saveErrand(Errand errand) {
		//Errand errand = convertToErrand(errandDto);
		errandRepository.save(errand);
	}

	@Override
	public ErrandDto saveOrUpdate(Errand errand) {
		return convertToErrandDto(errandRepository.save(errand));
	}
    @Override
    public List<ErrandDto> createErrands(List<ErrandDto> errandDtoList) {
        return null;
    }

    @Override
    public PageImpl<ErrandDto> findAll(Specification<Errand> spec, int page, int size) {
        Page<Errand> errandPage = errandRepository.findAll(spec, PageRequest.of(page, size));

        PageImpl<ErrandDto> errandDtoPage = new PageImpl<>();
        errandDtoPage.setContent(errandPage.getContent().stream().map(ErrandDto::new).collect(Collectors.toList()));
        errandDtoPage.setNumber(errandPage.getNumber());
        errandDtoPage.setSize(errandPage.getSize());
        errandDtoPage.setTotalPages(errandPage.getTotalPages());
        errandDtoPage.setTotalElements(errandPage.getTotalElements());

        return errandDtoPage;
    }

    @Override
    public List<ErrandDto> updateErrands(List<ErrandDto> errandDtoList) {

        ArrayList<ErrandDto> resultErrandDtoList = new ArrayList<>();
        if (!errandDtoList.isEmpty()) {
            for (ErrandDto errandDto : errandDtoList) {
                Errand errand = convertToErrand(errandDto);
                resultErrandDtoList.add(new ErrandDto(errandRepository.save(errand)));
            }
        }
        return resultErrandDtoList;
    }

    @Override
    public List<ErrandDto> deleteErrands(List<Long> idsList) {
        ArrayList<ErrandDto> resultErrandDtoList = new ArrayList<>();
        if (!idsList.isEmpty()) {
            for (Long id : idsList) {
                ErrandDto errandDto = null;
                Errand errand = errandRepository.findErrandById(id);
                if (errand != null) {
                    errand.setDeleted(true);
                    errandRepository.save(errand);
                    resultErrandDtoList.add(new ErrandDto(errand));
                }
            }
        }
        return resultErrandDtoList;
    }

    @Override
    public List<ErrandDto> removeErrands(List<Long> idsList) {
        ArrayList<ErrandDto> resultErrandDtoList = new ArrayList<>();
        if (!idsList.isEmpty()) {
            for (Long id : idsList) {
                ErrandDto errandDto = null;
                Errand errand = errandRepository.findErrandById(id);
                if (errand != null) {
                    errandRepository.delete(errand);
                    resultErrandDtoList.add(new ErrandDto(errand));
                }
            }
        }
        return resultErrandDtoList;
    }


    public Errand convertToErrand(ErrandDto errandDto) {

        //Ищем командировку по айди, иначе создаем новую
        Errand errand;
        if (errandDto.getId() == null) {
            errand = new Errand();
        } else errand = errandRepository.findErrandById(errandDto.getId());

        //Находим сотрудника по айди и записываем
        Employee employee = employeeRepository.findEmployeeById(errandDto.getEmployeeId());
        errand.setEmployee(employee);

        //Находим статус по имени и сохраняем
        ErrandStatusType errandStatusType = errandStatusTypeRepository.findErrandStatusTypeByStatus(errandDto.getStatusType());
        errand.setStatusType(errandStatusType);

        //Заполняем Детали из ДТО
        ErrandDetails errandDetails = errand.getErrandDetails();

        errand.getErrandDetails().setMatter(errandMatterTypeRepository.findErrandMatterTypeByMatter(errandDto.getMatter()));
        errand.getErrandDetails().setPlace(placeRepository.findPlaceByTitle(errandDto.getPlace()));
        errand.getErrandDetails().setComment(errandDto.getComment());
        errandDetails.setCreatedBy(employeeRepository.findEmployeeById(errandDto.getCreatedById()));
        errandDetails.setConfirmedOrRejectedBy(employeeRepository.findEmployeeById(errandDto.getConfirmedOrRejectedById()));
        errand.setErrandDetails(errandDetails);

        errand.setDateStart(errandDto.getDateStart());
        errand.setDateEnd(errandDto.getDateEnd());

        return errand;
    }


    @Override
    public ByteArrayInputStream findAllForReport(Specification<Errand> spec) {
        return ReportErrandExporterExcel.errandsToExcel(errandRepository.findAll(spec));
    }

    @Override
    public void updateErrands(ErrandDto errandDto) {
        //TODO
    }
	private ErrandDto convertToErrandDto(Errand errand) {
		
		ErrandDto resultErrandDto = null;
		if (errand != null) {
			resultErrandDto = new ErrandDto(errand);
//			resultErrandDto = new ErrandDto(
//					errand.getId(),
//					errand.getCreated(),
//					errand.getUpdated(),
//					errand.getStatusType().getStatus(),
//					errand.getDateStart(),
//					errand.getDateEnd(),
//					errand.getEmployee().getFirstName(),
//					errand.getEmployee().getMiddleName(),
//					errand.getEmployee().getLastName(),
//					errand.getEmployee().getPosition().getPosition(),
//					errand.getEmployee().getUser().getUserName(),
//					errand.getEmployee().getDepartment().getTitle(),
//					errand.getEmployee().getDepartment().getMaster().getFirstName(),
//					errand.getEmployee().getDepartment().getMaster().getMiddleName(),
//					errand.getEmployee().getDepartment().getMaster().getLastName(),
//					errand.getEmployee().getDepartment().getMaster().getUser().getUserName(),
//					errand.getErrandDetails().getMatter().getMatter(),
//					errand.getErrandDetails().getPlace().getTitle(),
//					errand.getErrandDetails().getPlace().getPlaceType().getType(),
//					errand.getErrandDetails().getComment(),
//					errand.getErrandDetails().getCreatedBy().getFirstName(),
//					errand.getErrandDetails().getCreatedBy().getMiddleName(),
//					errand.getErrandDetails().getCreatedBy().getLastName(),
//					errand.getErrandDetails().getConfirmedOrRejectedBy().getFirstName(),
//					errand.getErrandDetails().getConfirmedOrRejectedBy().getMiddleName(),
//					errand.getErrandDetails().getConfirmedOrRejectedBy().getLastName()
//			);
		}
		return resultErrandDto;

	}
}
