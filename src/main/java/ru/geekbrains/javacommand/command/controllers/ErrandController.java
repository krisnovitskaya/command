package ru.geekbrains.javacommand.command.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.javacommand.command.controllers.facade.ErrandControllerApi;
import ru.geekbrains.javacommand.command.dtos.ErrandDto;
import ru.geekbrains.javacommand.command.dtos.ErrandMatterDto;
import ru.geekbrains.javacommand.command.entities.*;
import ru.geekbrains.javacommand.command.exceptions.ResourceNotFoundException;
import ru.geekbrains.javacommand.command.repositories.specifications.ErrandSpecifications;
import ru.geekbrains.javacommand.command.services.*;
import ru.geekbrains.javacommand.command.util.ErrandEmailMessageAlertHelper;
import ru.geekbrains.javacommand.command.util.ErrandFilter;
import ru.geekbrains.javacommand.command.util.PageImpl;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/v1/errands")
@RequiredArgsConstructor
public class ErrandController implements ErrandControllerApi {

    private final ErrandMatterTypeService matterTypeService;
    private final ErrandService errandService;
    private final EmployeeService employeeService;
    private final UserService userService;
    private final EmailService emailService;
    private final DepartmentService departmentService;

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

    @GetMapping(value = "/report")
    public ResponseEntity<?> getReportFile(@RequestParam Map<String, String> params) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=report.xlsx");
        headers.add("Content-Type", "application/vnd.ms-excel");

        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(errandService.findAllForReport(new ErrandFilter(params).getSpec())));
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
    public ResponseEntity<?> create(List<ErrandDto> errandCreateDtoList) {
        return ResponseEntity.ok(errandService.createErrands(errandCreateDtoList));
    }

    @Override
    public void createErrand(ErrandDto newErrandDto, Principal principal) {
        var name = principal.getName();
        emailAlertWhenErrandCreated(newErrandDto, name);
    }

    @Override
    public ResponseEntity<?> updateErrandStatus(Long errandId, String status) {
        var errand = errandService.findErrandById(errandId);
        errand.setStatusType(status);
        errandService.updateErrands(errand);
        var responseMsg = "Email alert sent";
        emailAlertWhenErrandUpdated(errand);
        return ResponseEntity.ok(responseMsg);
    }

    /**
     * При создании новой командировки сотрудником, непосредственный начальник
     * получает оповещение на почту
     * @see EmailService
     * @param newErrandDto
     * @param name
     * @author owpk
     */
    private void emailAlertWhenErrandCreated(ErrandDto newErrandDto, String name) {
        var user = userService.findByUsername(name).orElse(new User());
        var isSimpleEmployee = user.getListRoles().stream()
                .anyMatch(x -> x.getName().equals("MASTER"));
        if (isSimpleEmployee) {
            var dep = departmentService.findDepartmentByDepartmentTitle(newErrandDto.getDepartmentTitle());
            var master= dep.getMaster();
            var msg = ErrandEmailMessageAlertHelper.generateEmailMessage(newErrandDto);
            emailService.sendSimpleMessage(master.getEmployeeDetails().getMail(), "Создана командровка", msg);
        }
    }

    /**
     * Создает email сообщение при смене статуса командировки на confirmed или reject, отправляет
     * оповещения на почту командируемого сотрудника
     * @see EmailService
     * @param errand
     * @author owpk
     */
    private void emailAlertWhenErrandUpdated(ErrandDto errand) {
        var employee = employeeService.findById(errand.getEmployeeId());
        var emailMsg = ErrandEmailMessageAlertHelper.generateEmailMessage(errand);
        emailService.sendSimpleMessage(
                employee.getEmployeeDetails().getMail(),
                "Статус командировки обновлен", emailMsg);
    }

    @Override
    public ResponseEntity<?> update(List<ErrandDto> errandUpdateDtoList) {
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
