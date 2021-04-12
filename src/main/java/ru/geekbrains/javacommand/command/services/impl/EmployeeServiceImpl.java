package ru.geekbrains.javacommand.command.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.dtos.EmployeeDto;
import ru.geekbrains.javacommand.command.dtos.ProfileDto;
import ru.geekbrains.javacommand.command.entities.Department;
import ru.geekbrains.javacommand.command.entities.Employee;
import ru.geekbrains.javacommand.command.entities.User;
import ru.geekbrains.javacommand.command.exceptions.ResourceNotFoundException;
import ru.geekbrains.javacommand.command.repositories.*;
import ru.geekbrains.javacommand.command.services.DepartmentService;
import ru.geekbrains.javacommand.command.services.EmployeeService;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;

    private final DepartmentService departmentService;

    public List<EmployeeDto> findAllByMaster(Employee master) {
        Department department = departmentService.findAllEmployeesByMaster(master);
        return employeeRepository.findAllByDepartment(department).stream().map(EmployeeDto::new).collect(Collectors.toList());
    }

    @Override
    public Optional<Employee> findByUser(User user) {
        return employeeRepository.findByUser(user);
    }
    
    @Override
    public ProfileDto getProfile(String username) {
        return new ProfileDto(employeeRepository.findEmployeeByUsername(username));
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public void saveOrUpdate(EmployeeDto employeeDto) {
        Employee newEmployee;
        if (employeeDto.getId() == null) {
            newEmployee = new Employee();
        } else {
            newEmployee = employeeRepository.findById(employeeDto.getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            String.format("Сотрудник с id = %s не найден", employeeDto.getId()))
                    );
        }
        newEmployee.setFirstName(employeeDto.getFirstName());
        newEmployee.setMiddleName(employeeDto.getMiddleName());
        newEmployee.setLastName(employeeDto.getLastName());
        newEmployee.setPosition(positionRepository.findPositionByPosition(employeeDto.getPositionName()));
        newEmployee.setDepartment(departmentRepository.findDepartmentByTitle(employeeDto.getDepartmentName()).orElse(new Department()));
//        newEmployee.setUser(userRepository.findByUserName(employeeDto.getUserName())
//            .orElseThrow(() -> new ResourceNotFoundException(
//                String.format("Учётная запись с userName = %s не найдена", employeeDto.getUserName()))
//            )
//        );
        employeeRepository.save(newEmployee);
    }

    @Override
    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll().stream().map(EmployeeDto::new).collect(Collectors.toList());
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Employee not found with id %s", id)));
    }
    public List<EmployeeDto> findAllByDepartmentId(Long id) {
        return employeeRepository.findAllByDepartmentId(id).stream().map(EmployeeDto::new).collect(Collectors.toList());
    }
    @Override
    public EmployeeDto findByUserId(Long id) {
        return new EmployeeDto(employeeRepository.findByUserId(id));
    }

    @Override
    public EmployeeDto findEmployeeById(Long id) {
        return new EmployeeDto(employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Сотрудник с id = %s не найден", id))
                )
        );
    }

    @Override
	public EmployeeDto findByUsername(String username) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
