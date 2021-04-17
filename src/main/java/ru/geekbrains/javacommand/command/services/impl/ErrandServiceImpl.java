/*
 * License Headers.
 */
package ru.geekbrains.javacommand.command.services.impl;

import java.io.ByteArrayInputStream;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.javacommand.command.dtos.CreatedErrandDto;
import ru.geekbrains.javacommand.command.dtos.ErrandDto;
import ru.geekbrains.javacommand.command.entities.*;
import ru.geekbrains.javacommand.command.exceptions.ResourceNotFoundException;
import ru.geekbrains.javacommand.command.repositories.*;
import ru.geekbrains.javacommand.command.repositories.specifications.ErrandSpecifications;
import ru.geekbrains.javacommand.command.services.contracts.ErrandService;
import ru.geekbrains.javacommand.command.services.contracts.UserService;
import ru.geekbrains.javacommand.command.util.ErrandFilter;
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
    private final UserService userService;

    private final Integer PAGE_SIZE = 5;


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
		return new ErrandDto(errandRepository.save(errand));
	}
    @Override
    public List<ErrandDto> createErrands(List<ErrandDto> errandDtoList) {
        return null;
    }

    @Override
    public PageImpl<ErrandDto> findErrandsByMaster(int page, Map<String, String> params, User user) {

        // get masterEmployee from Principal
        Employee master =
                employeeRepository
                        .findByUser(user)
                        .orElseThrow(() -> new ResourceNotFoundException("master not found"));

        // Prepare Spec Filter
        ErrandFilter errandFilter = new ErrandFilter(params);
        Specification<Errand> spec = errandFilter.getSpec();

        // Force Add departmentId to FilterSpec for nonAdmin Users
        if (!userService.isAdmin(user)) {
            spec = spec.and(ErrandSpecifications.departmentIdIs(master.getDepartment().getId()));
        }

        return findAll(spec, page - 1, PAGE_SIZE);
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
        ErrandStatusType errandStatusType = errandStatusTypeRepository.findErrandStatusTypeByStatus(errandDto.getStatusType()).get();
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

    @Override
    public void updateErrandStatus(Long errandId, ErrandStatusType errandStatusType) {
        Errand errand = errandRepository.findErrandById(errandId);
        errand.setStatusType(errandStatusType);
        errandRepository.save(errand);
    }

    @Override
    public void saveErrand(CreatedErrandDto errandDto) {
        Errand errand = new Errand();
        errand.setId(null);
        errand.setEmployee(employeeRepository.findByUserId(errandDto.getEmployeeId()));
        errand.setDateStart(errandDto.getDateStart());
        errand.setDateEnd(errandDto.getDateEnd());
        errand.setStatusType(errandStatusTypeRepository.findErrandStatusTypeByStatus(errandDto.getStatusType()).get());
        errand.setCreated(OffsetDateTime.now());
        errand.setUpdated(OffsetDateTime.now());
        errand.setDeleted(false);

        errand.setErrandDetails(new ErrandDetails());
        errand.getErrandDetails().setMatter(errandMatterTypeRepository.findErrandMatterTypeById(errandDto.getMatterId()));
        errand.getErrandDetails().setPlace(placeRepository.findPlaceById(errandDto.getPlaceId()));
        errand.getErrandDetails().setComment(errandDto.getComment());
        errand.getErrandDetails().setCreatedBy(employeeRepository.findEmployeeById(errandDto.getCreatedById()));
        errand.getErrandDetails().setConfirmedOrRejectedBy(employeeRepository.findEmployeeById(errandDto.getConfirmedOrRejectedById()));
        errand.getErrandDetails().setCreated(OffsetDateTime.now());
        errand.getErrandDetails().setUpdated(OffsetDateTime.now());

        errandRepository.save(errand);
    }

}
