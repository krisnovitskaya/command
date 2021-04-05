package ru.geekbrains.javacommand.command.services.impl;

import java.util.ArrayList;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.dtos.*;
import ru.geekbrains.javacommand.command.entities.Errand;
import ru.geekbrains.javacommand.command.repositories.ErrandRepository;
import ru.geekbrains.javacommand.command.services.ErrandService;
import ru.geekbrains.javacommand.command.util.PageImpl;
import ru.geekbrains.javacommand.command.entities.Employee;
import ru.geekbrains.javacommand.command.entities.ErrandDetails;
import ru.geekbrains.javacommand.command.entities.ErrandStatusType;
import ru.geekbrains.javacommand.command.repositories.EmployeeRepository;
import ru.geekbrains.javacommand.command.repositories.ErrandMatterTypeRepository;
import ru.geekbrains.javacommand.command.repositories.ErrandStatusTypeRepository;
import ru.geekbrains.javacommand.command.repositories.PlaceRepository;

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
    return errandRepository.findCurrent().stream().map(ErrandDto::new).collect(Collectors.toList());
  }

  @Override
  public ErrandDto findErrandById(Long id) {
    return new ErrandDto(errandRepository.findErrandById(id));
  }

  @Override
  public List<ErrandUpdateDto> createErrands(List<ErrandCreateDto> errandCreateDtoList) {
		
		ArrayList<ErrandUpdateDto> resultDtoList = new ArrayList<>();
    if (!errandCreateDtoList.isEmpty()) {
			for (ErrandCreateDto errandCreateDto : errandCreateDtoList) {
				Errand errand = convertToErrand(errandCreateDto);
				resultDtoList.add(convertToErrandUpdateDto(errandRepository.save(errand)));
			}
		}
    return resultDtoList;
  }

  @Override
  public List<ErrandUpdateDto> updateErrands(List<ErrandUpdateDto> errandUpdateDtoList) {

    ArrayList<ErrandUpdateDto> resultErrandUpdateDtoList = new ArrayList<>();
		if (!errandUpdateDtoList.isEmpty()) {
			for (ErrandUpdateDto errandUpdateDto : errandUpdateDtoList) {
				Errand errand = convertToErrand(errandUpdateDto);
				resultErrandUpdateDtoList.add(convertToErrandUpdateDto(errandRepository.save(errand)));
			}
		}
    return resultErrandUpdateDtoList;
  }
	
  @Override
  public PageImpl<ErrandDto> findAll(Specification<Errand> spec, int page, int size) {
    Page<Errand> errandPage = errandRepository.findAll(spec, PageRequest.of(page, size));

    PageImpl<ErrandDto> errandDtoPage = new PageImpl<>();
    errandDtoPage.setContent(
        errandPage.getContent().stream().map(ErrandDto::new).collect(Collectors.toList()));
    errandDtoPage.setNumber(errandPage.getNumber());
    errandDtoPage.setSize(errandPage.getSize());
    errandDtoPage.setTotalPages(errandPage.getTotalPages());
    errandDtoPage.setTotalElements(errandPage.getTotalElements());

    return errandDtoPage;
  }

  @Override
  public List<ErrandDeleteDto> deleteErrands(List<Long> idsList) {
    ArrayList<ErrandDeleteDto> resultDtoList = new ArrayList<>();
    if (!idsList.isEmpty()) {
      for (Long id : idsList) {
        ErrandDto errandDto = null;
        Errand errand = errandRepository.findErrandById(id);
        if (errand != null) {
          errand.setDeleted(1);
          errandRepository.save(errand);
          resultDtoList.add(new ErrandDeleteDto(id, 1));
        }
      }
    }
    return resultDtoList;
  }

  @Override
  public List<ErrandRemoveDto> removeErrands(List<Long> idsList) {
    ArrayList<ErrandRemoveDto> resultDtoList = new ArrayList<>();
    if (!idsList.isEmpty()) {
      for (Long id : idsList) {
        ErrandDto errandDto = null;
        Errand errand = errandRepository.findErrandById(id);
        if (errand != null) {
          errandRepository.delete(errand);
          resultDtoList.add(new ErrandRemoveDto(id));
        }
      }
    }
    return resultDtoList;
  }

	private ErrandUpdateDto convertToErrandUpdateDto(Errand errand) {
		ErrandDetailsDto errandDetailsDto =
				new ErrandDetailsDto(
						errand.getErrandDetails().getMatter().getId(),
						errand.getErrandDetails().getPlace().getId(),
						errand.getErrandDetails().getComment(),
						errand.getErrandDetails().getCreatedBy().getId(),
						errand.getErrandDetails().getConfirmedOrRejectedBy().getId());
    ErrandUpdateDto errandUpdateDto =
        new ErrandUpdateDto(
						errand.getId(),
            errand.getStatusType().getId(),
            errand.getEmployee().getId(),
						errandDetailsDto,
            errand.getDateStart(),
            errand.getDateEnd(),
						errand.getUpdated());
    return errandUpdateDto;
  }
	
	// TODO написать обратный маппер
  private Errand convertToErrand(ErrandDto errandDto) {
		return null;
//		Errand errand = new Errand();
//    Employee employee = employeeRepository.findEmployeeById(errandDto.getEmployeeId());
//    errand.setEmployee(employee);
//    ErrandStatusType errandStatusType =
//        errandStatusTypeRepository.findErrandStatusTypeById(errandDto.getStatusId());
//    errand.setStatusType(errandStatusType);
//    ErrandDetails errandDetails = null;
//    if (errandDto.getErrandDetailsDto() != null) {
//      if (errand.getErrandDetails() == null) {
//        errandDetails = new ErrandDetails();
//      } else {
//        errandDetails = errand.getErrandDetails();
//      }
//      ErrandDetailsDto errandDetailsDto = errandDto.getErrandDetailsDto();
//      errandDetails.setMatter(
//          errandMatterTypeRepository.findErrandMatterTypeById(
//              errandDetailsDto.getErrandMatterTypeId()));
//      errandDetails.setPlace(placeRepository.findPlaceById(errandDetailsDto.getPlaceId()));
//      errandDetails.setComment(errandDetailsDto.getComment());
//      errandDetails.setCreatedBy(
//          employeeRepository.findEmployeeById(errandDetailsDto.getCreatedById()));
//      errandDetails.setConfirmedOrRejectedBy(
//          employeeRepository.findEmployeeById(errandDetailsDto.getConfirmedOrRejectedById()));
//      errand.setErrandDetails(errandDetails);
//    }
//    errand.setDateStart(errandDto.getDateStart());
//    errand.setDateEnd(errandDto.getDateEnd());
//    return errand;
  }
  
	private <T extends ErrandCreateDto> Errand convertToErrand(T errandCreateDto) {
		
		Errand errand = null;
		if (errandCreateDto instanceof ErrandCreateDto) {
			errand = new Errand();
		}
		if (errandCreateDto instanceof ErrandUpdateDto) {
			errand = errandRepository.findErrandById(((ErrandUpdateDto) errandCreateDto).getId());
		}
    Employee employee = employeeRepository.findEmployeeById(errandCreateDto.getEmployeeId());
    errand.setEmployee(employee);
    ErrandStatusType errandStatusType =
        errandStatusTypeRepository.findErrandStatusTypeById(errandCreateDto.getStatusId());
    errand.setStatusType(errandStatusType);
		ErrandDetails errandDetails = null;
		if (errandCreateDto.getErrandDetailsDto() != null) {
			if (errand.getErrandDetails() == null) {
				errandDetails = new ErrandDetails();
			} else {
				errandDetails = errand.getErrandDetails();
			}
			ErrandDetailsDto errandDetailsDto = errandCreateDto.getErrandDetailsDto();
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
    errand.setDateStart(errandCreateDto.getDateStart());
    errand.setDateEnd(errandCreateDto.getDateEnd());
    return errand;
  }
}
