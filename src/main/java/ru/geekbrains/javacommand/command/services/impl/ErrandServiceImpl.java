/*
 * License Headers.
 */
package ru.geekbrains.javacommand.command.services.impl;

import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.entities.Errand;
import ru.geekbrains.javacommand.command.repositories.ErrandRepository;
import ru.geekbrains.javacommand.command.services.ErrandService;
import ru.geekbrains.javacommand.command.dtos.CurrentErrandDto;

import java.util.List;
import java.util.stream.Collectors;
import ru.geekbrains.javacommand.command.dtos.ErrandCreateDto;
import ru.geekbrains.javacommand.command.dtos.ErrandAboutInfoDto;
import ru.geekbrains.javacommand.command.dtos.ErrandDeleteDto;
import ru.geekbrains.javacommand.command.dtos.ErrandDetailsDto;
import ru.geekbrains.javacommand.command.dtos.ErrandRemoveDto;
import ru.geekbrains.javacommand.command.dtos.ErrandUpdateDto;
import ru.geekbrains.javacommand.command.entities.Employee;
import ru.geekbrains.javacommand.command.entities.ErrandDetails;
import ru.geekbrains.javacommand.command.entities.ErrandStatusType;
import ru.geekbrains.javacommand.command.repositories.EmployeeRepository;
import ru.geekbrains.javacommand.command.repositories.ErrandMatterTypeRepository;
import ru.geekbrains.javacommand.command.repositories.ErrandStatusTypeRepository;
import ru.geekbrains.javacommand.command.repositories.PlaceRepository;

@Service
@RequiredArgsConstructor
public class ErrandServiceImpl implements ErrandService {

  @Autowired private final ErrandRepository errandRepository;
  @Autowired private final EmployeeRepository employeeRepository;
  @Autowired private final ErrandStatusTypeRepository errandStatusTypeRepository;
  @Autowired private final ErrandMatterTypeRepository errandMatterTypeRepository;
  @Autowired private final PlaceRepository placeRepository;

  @Override
  public List<CurrentErrandDto> getListCurrent() {

    return errandRepository.findCurrent().stream()
        .map(CurrentErrandDto::new)
        .collect(Collectors.toList());
  }

  @Override
  public ErrandAboutInfoDto findErrandById(Long id) {

    return convertToErrandAboutDto(errandRepository.findErrandById(id));
  }

  @Override
  public List<ErrandUpdateDto> createErrands(List<ErrandCreateDto> errandCreateDtoList) {
		
		ArrayList<ErrandUpdateDto> resultErrandUpdateDtoList = new ArrayList<>();
    if (!errandCreateDtoList.isEmpty()) {
			for (ErrandCreateDto errandCreateDto : errandCreateDtoList) {
				Errand errand = convertToErrand(errandCreateDto);
				resultErrandUpdateDtoList.add(convertToErrandUpdateDto(errandRepository.save(errand)));
			}
		}
    return resultErrandUpdateDtoList;
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
	public List<ErrandDeleteDto> deleteErrands(List<Long> idsList) {
		ArrayList<ErrandDeleteDto> resultErrandDeleteDtoList = new ArrayList<>();
		if (!idsList.isEmpty()) {
			for (Long id : idsList) {
				ErrandDeleteDto errandDeleteDto = null;
        Errand errand = errandRepository.findErrandById(id);
				if (errand != null) {
          errand.setDeleted(1);
					errandRepository.save(errand);
					errandDeleteDto = new ErrandDeleteDto(id, errand.getDeleted());
				} else {
					errandDeleteDto = new ErrandDeleteDto(id, 0);
				}
				resultErrandDeleteDtoList.add(errandDeleteDto);
			}
		}
		return resultErrandDeleteDtoList;
	}

	@Override
	public List<ErrandRemoveDto> removeErrands(List<Long> idsList) {
		ArrayList<ErrandRemoveDto> resultErrandRemoveDtoList = new ArrayList<>();
		if (!idsList.isEmpty()) {
			for (Long id : idsList) {
				ErrandRemoveDto errandRemoveDto = null;
				Errand errand = errandRepository.findErrandById(id);
				if (errand != null) {
          errandRepository.delete(errand);
					errandRemoveDto = new ErrandRemoveDto(id);
				}
				resultErrandRemoveDtoList.add(errandRemoveDto);
			}
		}
		return resultErrandRemoveDtoList;
	}

	private ErrandAboutInfoDto convertToErrandAboutDto(Errand errand) {

    ErrandAboutInfoDto errandAboutInfoDto = null;
    if (errand != null) {
      errandAboutInfoDto =
          new ErrandAboutInfoDto(
              errand.getStatusType().getStatus(),
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
              errand.getErrandDetails().getConfirmedOrRejectedBy().getLastName());
    }
    return errandAboutInfoDto;
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

  private <T extends ErrandCreateDto> Errand convertToErrand(T errandDto) {
		
		Errand errand = null;
		if (errandDto instanceof ErrandCreateDto) {
			errand = new Errand();
		}
		if (errandDto instanceof ErrandUpdateDto) {
			errand = errandRepository.findErrandById(((ErrandUpdateDto) errandDto).getId());
		}
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
}
