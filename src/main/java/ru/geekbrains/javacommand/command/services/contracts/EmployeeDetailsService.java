package ru.geekbrains.javacommand.command.services.contracts;

import ru.geekbrains.javacommand.command.dtos.EmployeeDetailsDto;

import java.util.List;

public interface EmployeeDetailsService {

    EmployeeDetailsDto findDetailsByEmployeeId (Long id);

    void saveOrUpdate(EmployeeDetailsDto employeeDetailsDto);

    void create(EmployeeDetailsDto employeeDetailsDto);

    List<EmployeeDetailsDto> findAll();
}
