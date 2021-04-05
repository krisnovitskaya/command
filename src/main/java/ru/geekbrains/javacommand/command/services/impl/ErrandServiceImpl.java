package ru.geekbrains.javacommand.command.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.dtos.ErrandDetailsDto;
import ru.geekbrains.javacommand.command.dtos.ErrandDto;
import ru.geekbrains.javacommand.command.entities.Employee;
import ru.geekbrains.javacommand.command.entities.Errand;
import ru.geekbrains.javacommand.command.entities.ErrandDetails;
import ru.geekbrains.javacommand.command.entities.ErrandStatusType;
import ru.geekbrains.javacommand.command.repositories.*;
import ru.geekbrains.javacommand.command.services.ErrandService;
import ru.geekbrains.javacommand.command.util.PageImpl;
import ru.geekbrains.javacommand.command.util.ReportErrandExporterExcel;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ErrandServiceImpl implements ErrandService {

    private final ErrandRepository errandRepository;
    private final EmployeeRepository employeeRepository;
    private final ErrandStatusTypeRepository errandStatusTypeRepository;
    private final ErrandMatterTypeRepository errandMatterTypeRepository;
    private final PlaceRepository placeRepository;


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


    //TODO написать обратный маппер
    private <T extends ErrandDto> Errand convertToErrand(T errandDto) {

        Employee employee = employeeRepository.findEmployeeById(errandDto.getEmployeeId());
        errand.setEmployee(employee);
        ErrandStatusType errandStatusType =
                errandStatusTypeRepository.findErrandStatusTypeById(errandDto.getStatusId());
        errand.setStatusType(errandStatusType);
        ErrandDetails errandDetails = null;
        if (errandDto.getErrandDetailsDto() != null) {
            if (errand.getErrandDetails() == null) {
                errandDetails = new ErrandDetails();
            } else {
                errandDetails = errand.getErrandDetails();
            }
            ErrandDetailsDto errandDetailsDto = errandDto.getErrandDetailsDto();
            errandDetails.setMatter(
                    errandMatterTypeRepository.findErrandMatterTypeById(
                            errandDetailsDto.getErrandMatterTypeId()));
            errandDetails.setPlace(placeRepository.findPlaceById(errandDetailsDto.getPlaceId()));
            errandDetails.setComment(errandDetailsDto.getComment());
            errandDetails.setCreatedBy(
                    employeeRepository.findEmployeeById(errandDetailsDto.getCreatedById()));
            errandDetails.setConfirmedOrRejectedBy(
                    employeeRepository.findEmployeeById(errandDetailsDto.getConfirmedOrRejectedById()));
            errand.setErrandDetails(errandDetails);
        }
        errand.setDateStart(errandDto.getDateStart());
        errand.setDateEnd(errandDto.getDateEnd());
        return errand;
    }

    @Override
    public ByteArrayInputStream findAllForReport(Specification<Errand> spec){
        return ReportErrandExporterExcel.errandsToExcel(errandRepository.findAll(spec));
    }
}
