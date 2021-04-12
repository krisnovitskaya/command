package ru.geekbrains.javacommand.command.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.javacommand.command.controllers.facade.ErrandControllerApi;
import ru.geekbrains.javacommand.command.dtos.*;
import ru.geekbrains.javacommand.command.entities.*;
import ru.geekbrains.javacommand.command.exceptions.ResourceNotFoundException;
import ru.geekbrains.javacommand.command.repositories.specifications.ErrandSpecifications;
import ru.geekbrains.javacommand.command.services.contracts.*;
import ru.geekbrains.javacommand.command.util.ErrandEmailMessageAlertHelper;
import ru.geekbrains.javacommand.command.util.ErrandFilter;

import java.security.Principal;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

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
    private final PlaceService placeService;
    private final PlaceTypeService placeTypeService;
    private final ErrandDetailsService errandDetailsService;
    private final ErrandStatusTypeService errandStatusTypeService;


    /**
     * Возвращает список командировок для всех подчиненных сотрудников, в любом статусе, с учетом параметров
     * фильтрации
     * Для Роли Администратор возвращает все командировки
     *
     * @param page
     * @param params
     * @author jackwizard88
     */
    @Override
    public ResponseEntity<?> getAllErrands(
            @RequestParam(defaultValue = "1", name = "p") Integer page,
            @RequestParam Map<String, String> params,
            Principal principal) {

        if (page < 1) {
            page = 1;
        }

        User user = userService.findByUsername(principal.getName()).get();

        return ResponseEntity.ok(errandService.findErrandsByMaster(page, params, user));
    }


    @Override
    public ResponseEntity<?> findById(Long id) {
        return ResponseEntity.ok(errandService.findErrandById(id));
    }

    /**
     * Через POST запрос (для защиты от инъекций с фронта) возвращает ErrandDto для вывода дополнительной информаци
     *
     * @param id
     *
     * @author jackwizard88
     */
    @Override
    public ResponseEntity<?> getErrandDetails(Long id) {
        return ResponseEntity.ok(findById(id));
    }

    @Override
    public ResponseEntity<?> create(ErrandDto errandDto) {
        throw new UnsupportedOperationException(
                "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResponseEntity<?> createList(List<ErrandDto> errandDto) {
        return ResponseEntity.ok(errandService.createErrands(errandDto));
    }

    @Override
    public void createErrand(ErrandDto newErrandDto, Principal principal) {
        var name = principal.getName();
        emailAlertWhenErrandCreated(newErrandDto, name);
    }

    @Override
    public void createErrandNew(NewErrandDto newErrandDto) {
        ErrandMatterDto errandMatterTypeDto = matterTypeService.findById(newErrandDto.getMatterId());
        ErrandMatterType errandMatterType =
                matterTypeService.convertToErrandMatterType(errandMatterTypeDto);

        PlaceDto placeDto = placeService.findById(newErrandDto.getPlaceId());
        Place place = placeService.convertToPlace(placeDto);

        // EmployeeDto employeeDto = employeeService.findById(newErrandDto.getEmployeeId());
        // Employee employee = employeeService.convertToEmployee(employeeDto);
        Employee employee = employeeService.findById(newErrandDto.getEmployeeId());

        ErrandDetails newErrandDetails =
                new ErrandDetails(errandMatterType, place, newErrandDto.getComment(), employee);
        newErrandDetails.setId(null);

        errandDetailsService.save(newErrandDetails);

        Errand newErrand =
                new Errand(
                        employee, newErrandDetails, newErrandDto.getDateStart(), newErrandDto.getDateEnd());
        newErrand.setId(null);

        errandService.saveErrand(newErrand);

        newErrandDetails.setCreated(OffsetDateTime.now());
        newErrandDetails.setUpdated(OffsetDateTime.now());
        newErrand.setCreated(OffsetDateTime.from(ZonedDateTime.now()));
        newErrand.setUpdated(OffsetDateTime.from(ZonedDateTime.now()));

        if (newErrand.getEmployee().getId() == 1) { // начальник
            newErrand.setStatusType(
                    errandStatusTypeService.returnFromDto(errandStatusTypeService.findById(2L)));
            newErrandDetails.setConfirmedOrRejectedBy(newErrand.getEmployee());
        } else {
            newErrand.setStatusType(
                    errandStatusTypeService.returnFromDto(errandStatusTypeService.findById(1L)));
        } // остальные сотрудники

        System.out.println(
                " getEmployee().getId()  "
                        + newErrand.getEmployee().getId()
                        + " newErrand.getErranddet().getId()  "
                        + newErrand.getErrandDetails().getId()
                        + " newErrand.getDateStart()  "
                        + newErrand.getDateStart()
                        + " newErrand.getstatus  "
                        + newErrand.getStatusType()
                        + " newErrand.getDateEnd()  "
                        + newErrand.getDateEnd());
        System.out.println(
                " errandDetails.getMatter().getMatter()  "
                        + newErrandDetails.getMatter().getMatter()
                        + " newErrandDetails.getPlace() "
                        + newErrandDetails.getPlace().getTitle()
                        + "  newErrandDetails.getComment()  "
                        + newErrandDetails.getComment()
                        + "  newErrandDetails.getdate  "
                        + newErrandDetails.getCreated()
                        + "  newErrandDetails.cjnf  "
                        + newErrandDetails.getConfirmedOrRejectedBy());
    }

    @Override
    public ResponseEntity<?> update(List<ErrandDto> errandUpdateDtoList) {
        return ResponseEntity.ok(errandService.updateErrands(errandUpdateDtoList));
    }

    @Override
    public ResponseEntity<?> updateById(Long id, ErrandDto errandDto) {
        throw new UnsupportedOperationException(
                "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Сохраняет новый статус командировки
     *
     * @param errandId
     * @param status
     *
     * @author jackwizard88
     */
    @Override
    public ResponseEntity<?> updateErrandStatus(Long errandId, String status) {
        ErrandStatusType errandStatusType = errandStatusTypeService.findByStatus(status).orElseThrow(
                () -> new ResourceNotFoundException(String.format("%s status not found", status)));
        errandService.updateErrandStatus(errandId, errandStatusType);
//        emailAlertWhenErrandUpdated(errandService.findErrandById(errandId)); //TODO тут какие то ошибки
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
        throw new UnsupportedOperationException(
                "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResponseEntity<?> deleteByIds(List<Long> idsList) {
        return ResponseEntity.ok(errandService.deleteErrands(idsList));
    }

    @Override
    public ResponseEntity<?> removeById(Long id) {
        throw new UnsupportedOperationException(
                "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResponseEntity<?> removeByIds(List<Long> idsList) {
        return ResponseEntity.ok(errandService.removeErrands(idsList));
    }

    @Override
    public List<ErrandMatterDto> getMatters() {
        return matterTypeService.findAll();
    }

    /**
     * Возвращает все имеющиеся ErrandStatusTypeDto
     *
     * @author jackwizard88
     */
    @Override
    public List<ErrandStatusTypeDto> getStatuses() {
        return errandStatusTypeService.findAll();
    }

    @Override
    public List<PlaceTypeDto> getPlaceTypes() {
        return placeTypeService.findAll();
    }

    @Override
    public List<PlaceDto> getPlaces(Long placeTypeId) {
        return placeService.findAllByPlaceTypeId(placeTypeId);
    }

    /**
     * При создании новой командировки сотрудником, непосредственный начальник получает оповещение на
     * почту
     *
     * @param newErrandDto
     * @param name
     * @author owpk
     * @see EmailService
     */
    private void emailAlertWhenErrandCreated(ErrandDto newErrandDto, String name) {
        var user = userService.findByUsername(name).get();
        var isMaster = user.getListRoles().stream().anyMatch(x -> x.getName().equals("MASTER"));
        if (!isMaster) {
            var dep =
                    departmentService.findDepartmentByDepartmentTitle(newErrandDto.getDepartmentTitle());
            var master = dep.getMaster();
            var msg = ErrandEmailMessageAlertHelper.generateEmailMessage(newErrandDto);
            emailService.sendSimpleMessage(
                    master.getEmployeeDetails().getMail(), "Создана командровка", msg);
        }
    }

    /**
     * Создает email сообщение при смене статуса командировки на confirmed или reject, отправляет
     * оповещения на почту командируемого сотрудника
     *
     * @param errand
     * @author owpk
     * @see EmailService
     */
    private void emailAlertWhenErrandUpdated(ErrandDto errand) {
        var employee = employeeService.findById(errand.getEmployeeId());
        var emailMsg = ErrandEmailMessageAlertHelper.generateEmailMessage(errand);
        emailService.sendSimpleMessage(
                employee.getEmployeeDetails().getMail(), "Статус командировки обновлен", emailMsg);
    }

    @Override
    public ResponseEntity<?> getReportFile(@RequestParam Map<String, String> params) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=report.xlsx");
        headers.add("Content-Type", "application/vnd.ms-excel");

    return ResponseEntity.ok()
        .headers(headers)
        .body(
            new InputStreamResource(
                errandService.findAllForReport(new ErrandFilter(params).getSpec())));
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
    User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException(String.format("User '%s' not found", principal.getName())));
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
    System.out.println("created " + errandDto.getCreatedById());
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
