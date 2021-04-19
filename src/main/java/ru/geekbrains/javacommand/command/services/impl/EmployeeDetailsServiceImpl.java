package ru.geekbrains.javacommand.command.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.dtos.EmployeeDetailsDto;
import ru.geekbrains.javacommand.command.entities.EmployeeDetails;
import ru.geekbrains.javacommand.command.exceptions.ResourceNotFoundException;
import ru.geekbrains.javacommand.command.repositories.EmployeeDetailsRepository;
import ru.geekbrains.javacommand.command.services.contracts.EmployeeDetailsService;

@Service
@RequiredArgsConstructor
public class EmployeeDetailsServiceImpl implements EmployeeDetailsService {

    private final EmployeeDetailsRepository employeeDetailsRepository;

    @Override
    public EmployeeDetailsDto findDetailsByEmployeeId(Long id) {
        return new EmployeeDetailsDto(employeeDetailsRepository.findEmployeeDetailsByEmployeeId(id));
    }

    @Override
    public void saveOrUpdate(EmployeeDetailsDto employeeDetailsDto) {
        EmployeeDetails newEmployeeDetails;
        newEmployeeDetails = employeeDetailsRepository.findById(employeeDetailsDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Детализация с id = %s не найдена", employeeDetailsDto.getId()))
                );
        newEmployeeDetails.setMail(employeeDetailsDto.getMail());
        employeeDetailsRepository.save(newEmployeeDetails);
    }
}
