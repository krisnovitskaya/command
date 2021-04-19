package ru.geekbrains.javacommand.command.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.dtos.EmployeeDetailsDto;
import ru.geekbrains.javacommand.command.dtos.EmployeeDto;
import ru.geekbrains.javacommand.command.entities.Employee;
import ru.geekbrains.javacommand.command.entities.EmployeeDetails;
import ru.geekbrains.javacommand.command.exceptions.ResourceNotFoundException;
import ru.geekbrains.javacommand.command.repositories.EmployeeDetailsRepository;
import ru.geekbrains.javacommand.command.repositories.EmployeeRepository;
import ru.geekbrains.javacommand.command.services.contracts.EmployeeDetailsService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeDetailsServiceImpl implements EmployeeDetailsService {

    private final EmployeeDetailsRepository employeeDetailsRepository;
    private final EmployeeRepository employeeRepository;

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

    @Override
    public void create(EmployeeDetailsDto employeeDetailsDto) {
        EmployeeDetails newEmployeeDetails = new EmployeeDetails();
        Employee employee = employeeRepository.findById(employeeDetailsDto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Сотрудник с id = %s не найден", employeeDetailsDto.getEmployeeId()))
                );
        newEmployeeDetails.setMail(employeeDetailsDto.getMail());
        newEmployeeDetails.setEmployee(employee);
        employeeDetailsRepository.save(newEmployeeDetails);
    }

    @Override
    public List<EmployeeDetailsDto> findAll() {
        return employeeDetailsRepository.findAll().stream().map(EmployeeDetailsDto::new).collect(Collectors.toList());
    }
}
