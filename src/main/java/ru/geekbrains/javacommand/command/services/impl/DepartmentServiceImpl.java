package ru.geekbrains.javacommand.command.services.impl;

import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.dtos.DepartmentDto;
import ru.geekbrains.javacommand.command.dtos.DepartmentSimpleDto;
import ru.geekbrains.javacommand.command.entities.Department;
import ru.geekbrains.javacommand.command.entities.Employee;
import ru.geekbrains.javacommand.command.entities.User;
import ru.geekbrains.javacommand.command.exceptions.ResourceNotFoundException;
import ru.geekbrains.javacommand.command.repositories.DepartmentRepository;
import ru.geekbrains.javacommand.command.services.contracts.DepartmentService;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentDto> findAll() {
        return departmentRepository.findAll().stream().map(DepartmentDto :: new).collect(Collectors.toList());
    }

    @Override
    public Department findAllEmployeesByMaster(Employee master) {
        return departmentRepository.findByMaster(master).orElseThrow(() -> new ResolutionException("Department not found"));
    }

    @Override
    public Department findDepartmentByDepartmentTitle(String departmentTitle) {
        return departmentRepository.findDepartmentByTitle(departmentTitle)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Department not found by title: %s", departmentTitle)));
    }

    @Override
    public List<DepartmentSimpleDto> getSubordinateDepartments(Long id) {
        return departmentRepository.getSubordinateDepartments(id).stream().map(DepartmentSimpleDto::new).collect(Collectors.toList());
    }



    @Override
    public List<DepartmentSimpleDto> findAllById(Long id) {
        List<Department> listFull = departmentRepository.findAll();

        List<Department> list = new ArrayList<>();
        //достаем текущий департамент
        for (Department department : listFull) {
            if(department.getId().equals(id)){
                list.add(department);
                break;
            }
        }

        for(int i = 0; i < list.size(); i++){
            Department dep = list.get(i);
            for (Department department : listFull) {
                if(department.getMasterDepartment() == null) continue;
                if(department.getMasterDepartment().getId().equals(dep.getId())){
                    list.add(department);
                }
            }
        }

        return list.stream().map(DepartmentSimpleDto::new).collect(Collectors.toList());
    }

}
