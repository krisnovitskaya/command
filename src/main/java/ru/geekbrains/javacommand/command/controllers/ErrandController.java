package ru.geekbrains.javacommand.command.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.javacommand.command.controllers.facade.ErrandControllerApi;
import ru.geekbrains.javacommand.command.dtos.ErrandDto;
import ru.geekbrains.javacommand.command.dtos.ErrandUpdateDto;
import ru.geekbrains.javacommand.command.dtos.ErrandMatterDto;
import ru.geekbrains.javacommand.command.entities.Employee;
import ru.geekbrains.javacommand.command.entities.Errand;
import ru.geekbrains.javacommand.command.entities.Role;
import ru.geekbrains.javacommand.command.entities.User;
import ru.geekbrains.javacommand.command.exceptions.ResourceNotFoundException;
import ru.geekbrains.javacommand.command.repositories.specifications.ErrandSpecifications;
import ru.geekbrains.javacommand.command.services.EmployeeService;
import ru.geekbrains.javacommand.command.services.ErrandMatterTypeService;
import ru.geekbrains.javacommand.command.services.ErrandService;
import ru.geekbrains.javacommand.command.services.UserService;
import ru.geekbrains.javacommand.command.util.ErrandFilter;
import ru.geekbrains.javacommand.command.util.PageImpl;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import ru.geekbrains.javacommand.command.dtos.ErrandCreateDto;

@RestController
@RequestMapping("/api/v1/errands")
@RequiredArgsConstructor
public class ErrandController implements ErrandControllerApi {

    private final ErrandMatterTypeService matterTypeService;
    private final ErrandService errandService;
    private final EmployeeService employeeService;
    private final UserService userService;

    //TODO изменить формат вывода даты на читаемый
    @GetMapping(value = "/pending", produces = "application/json")
    public PageImpl<ErrandDto> getAllErrands(@RequestParam(defaultValue = "1", name = "p") Integer page,
                                             @RequestParam Map<String, String> params,
                                             Principal principal
    ) {
        if (page < 1) {
            page = 1;
        }
        //TODO по мне это костыль, но я пока не придумал как сделать элегантнее
        //Prepare Spec Filter
        ErrandFilter errandFilter = new ErrandFilter(params);
        Specification<Errand> spec = errandFilter.getSpec();

        //get masterEmployee from Principal
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        Employee master = employeeService.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("master not found"));

        for (Role role : user.getListRoles()) {
            if (role.getName().equals("ROLE_ADMIN")) {
                return errandService.findAll(spec, page - 1, 5);
            }
        }
        //Force Add departmentId to FilterSpec
        spec = spec.and(ErrandSpecifications.departmentIdIs(master.getDepartment().getId()));

        return errandService.findAll(spec, page - 1, 5);
    }

    @GetMapping(value = "/types", produces = "application/json")
    public List<ErrandMatterDto> getErrandMatters() {
        return getMatters();
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        return ResponseEntity.ok(errandService.findErrandById(id));
    }

    @Override
    public ResponseEntity<?> create(List<ErrandCreateDto> errandCreateDtoList) {
        return ResponseEntity.ok(errandService.createErrands(errandCreateDtoList));
    }

    @Override
    public ResponseEntity<?> update(List<ErrandUpdateDto> errandUpdateDtoList) {
        return ResponseEntity.ok(errandService.updateErrands(errandUpdateDtoList));
    }

    @Override
    public ResponseEntity<?> deleteByIds(List<Long> idsList) {
        return ResponseEntity.ok(errandService.deleteErrands(idsList));
    }

    @Override
    public ResponseEntity<?> removeByIds(List<Long> idsList) {
        return ResponseEntity.ok(errandService.removeErrands(idsList));
    }

    @Override
    public List<ErrandMatterDto> getMatters() {
        return matterTypeService.findAll();
    }

}
