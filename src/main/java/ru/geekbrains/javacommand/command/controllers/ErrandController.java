package ru.geekbrains.javacommand.command.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.javacommand.command.controllers.facade.ErrandControllerApi;
import ru.geekbrains.javacommand.command.dtos.*;
import ru.geekbrains.javacommand.command.entities.*;
import ru.geekbrains.javacommand.command.exceptions.ResourceNotFoundException;
import ru.geekbrains.javacommand.command.repositories.specifications.ErrandSpecifications;
import ru.geekbrains.javacommand.command.services.*;
import ru.geekbrains.javacommand.command.util.ErrandEmailMessageAlertHelper;
import ru.geekbrains.javacommand.command.util.ErrandFilter;
import ru.geekbrains.javacommand.command.util.PageImpl;

import java.security.Principal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/v1/errands")
@RequiredArgsConstructor
public class ErrandController implements ErrandControllerApi {

    private final ErrandMatterTypeService matterTypeService;
    private final PlaceService placeService;
    private final PlaceTypeService placeTypeService;
    private final ErrandService errandService;
    private final EmployeeService employeeService;
    private final UserService userService;
    private final EmailService emailService;
    private final DepartmentService departmentService;
    private final ErrandDetailsService errandDetailsService;
    private final ErrandStatusTypeService errandStatusTypeService;

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
        User user = userService.findByUsername(principal.getName()).get();
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
        var user = userService.findByUsername(name).get();
        var isMaster= user.getListRoles().stream()
                .anyMatch(x -> x.getName().equals("MASTER"));
        if (!isMaster) {
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

    @Override
    public List<PlaceTypeDto> getPlaceTypes() {
        return placeTypeService.findAll();
    }

    @Override
    public List<PlaceDto> getPlaces(Long id) {
        return placeService.findAllByPlaceTypeId(id);
    }

    @Override
    public void createNewErrand(ErrandDto errandDto, Principal principal) {
//        errandService.saveErrand();
//        errandDetailsService.saveErrandDetails();
//        ErrandDto errand = new ErrandDto();
//        errand.setId(null);
//        errand.setDateStart(errandDto.getDateStart());
//        errand.setDateEnd(errandDto.getDateEnd());
//        errand.setEmployeeId(errandDto.getEmployeeId());
//        errandDto.setCreated(OffsetDateTime.now());
//        errandDto.setUpdated(OffsetDateTime.now());


//        ErrandDetailsDto errandDetails = new ErrandDetailsDto();
//        errandDetails.setId(null);
//        errandDetails.setMatter(matterTypeService.findErrandMatterTypeById(errandDto.getMatterId()));
//        errandDetails.setPlaceId(errandDto.getPlaceId());
//        errandDetails.setComment(errandDto.getComment());
//        errandDetails.setCreatedBy(employeeService.findById(errandDto.getEmployeeId()));
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", principal.getName())));
        Employee employee = employeeService.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Employee for user not found"));

        if (user.getListRoles().contains("MASTER")){// начальник
            errandDto.setStatusType(errandStatusTypeService.findById(2L).getStatus());
            errandDto.setConfirmedOrRejectedById(employee.getId());
            errandDto.getErrandDetails().setConfirmedOrRejectedBy(employee);
        }
        else {
            errandDto.setStatusType(errandStatusTypeService.findById(1L).getStatus());
        }
//        errand.setErrandDetails(errandDetails);

        System.out.println("errandDto.getEmployeeId() " + errandDto.getEmployeeId());
        System.out.println("matter) " + errandDto.getMatterId());
        System.out.println("place " + errandDto.getPlaceId());
        System.out.println("comment " + errandDto.getComment());
        System.out.println("start " + errandDto.getDateStart());
        System.out.println("end " + errandDto.getDateEnd());
        System.out.println("status " + errandDto.getStatusType());
//        System.out.println("conf " + errandDto.getErrandDetails().getConfirmedOrRejectedBy().getId());

        errandService.saveErrand(errandDto);
//        errandDetailsService.saveErrandDetails(errandDetails);


        // остальные сотрудники
//        ErrandMatterDto errandMatterTypeDto = matterTypeService.findById(newErrandDto.getMatterId());
//        ErrandMatterType errandMatterType = matterTypeService.convertToErrandMatterType(errandMatterTypeDto);
//
//        PlaceDto placeDto = placeService.findById(newErrandDto.getPlaceId());
//        Place place = placeService.convertToPlace(placeDto);
//
//        EmployeeDto employeeDto = employeeService.findById(newErrandDto.getEmployeeId());
//        Employee employee = employeeService.convertToEmployee(employeeDto);
//
//        ErrandDetails newErrandDetails = new ErrandDetails(errandMatterType, place, newErrandDto.getComment(), employee);
//        newErrandDetails.setId(null);
//
//        errandDetailsService.save(newErrandDetails);
//
//        Errand newErrand = new Errand(employee, newErrandDetails, newErrandDto.getDateStart(), newErrandDto.getDateEnd());
//        newErrand.setId(null);
//
//        errandService.saveErrand(newErrand);
//
//        newErrandDetails.setCreated(OffsetDateTime.now());
//        newErrandDetails.setUpdated(OffsetDateTime.now());
//        newErrand.setCreated(OffsetDateTime.from(ZonedDateTime.now()));
//        newErrand.setUpdated(OffsetDateTime.from(ZonedDateTime.now()));
//
//        if (newErrand.getEmployee().getId() == 1){// начальник
//            newErrand.setStatusType(errandStatusTypeService.returnFromDto(errandStatusTypeService.findById(2L)));
//            newErrandDetails.setConfirmedOrRejectedBy(newErrand.getEmployee());
//        }
//        else {
//            newErrand.setStatusType(errandStatusTypeService.returnFromDto(errandStatusTypeService.findById(1L)));
//        }// остальные сотрудники
//
//
//        System.out.println(" getEmployee().getId()  " + newErrand.getEmployee().getId() +
//                " newErrand.getErranddet().getId()  " + newErrand.getErrandDetails().getId() +
//                " newErrand.getDateStart()  " + newErrand.getDateStart() +
//                " newErrand.getstatus  " + newErrand.getStatusType() +
//                " newErrand.getDateEnd()  " + newErrand.getDateEnd());
//        System.out.println(" errandDetails.getMatter().getMatter()  " + newErrandDetails.getMatter().getMatter() +
//                " newErrandDetails.getPlace() " + newErrandDetails.getPlace().getTitle() +
//                "  newErrandDetails.getComment()  " + newErrandDetails.getComment() +
//                "  newErrandDetails.getdate  " + newErrandDetails.getCreated()+
//                "  newErrandDetails.cjnf  " + newErrandDetails.getConfirmedOrRejectedBy());

    }


}