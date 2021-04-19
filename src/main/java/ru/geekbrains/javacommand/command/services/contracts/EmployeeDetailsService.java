package ru.geekbrains.javacommand.command.services.contracts;

import ru.geekbrains.javacommand.command.dtos.EmployeeDetailsDto;

public interface EmployeeDetailsService {

    EmployeeDetailsDto findDetailsByEmployeeId (Long id);

    void saveOrUpdate(EmployeeDetailsDto employeeDetailsDto);
}
