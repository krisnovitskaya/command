/*
 * License Headers.
 */

package ru.geekbrains.javacommand.command.controllers;

import java.security.Principal;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
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
import ru.geekbrains.javacommand.command.util.PageImpl;

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

  // TODO изменить формат вывода даты на читаемый
  @Override
  public PageImpl<ErrandDto> getAllErrands(
      @RequestParam(defaultValue = "1", name = "p") Integer page,
      @RequestParam Map<String, String> params,
      Principal principal) {
    if (page < 1) {
      page = 1;
    }
    // TODO по мне это костыль, но я пока не придумал как сделать элегантнее
    // Prepare Spec Filter
    ErrandFilter errandFilter = new ErrandFilter(params);
    Specification<Errand> spec = errandFilter.getSpec();

    // get masterEmployee from Principal
    User user = userService.findByUsername(principal.getName()).get();
    Employee master =
        employeeService
            .findByUser(user)
            .orElseThrow(() -> new ResourceNotFoundException("master not found"));

    for (Role role : user.getListRoles()) {
      if (role.getName().equals("ROLE_ADMIN")) {
        return errandService.findAll(spec, page - 1, 5);
      }
    }
    // Force Add departmentId to FilterSpec
    spec = spec.and(ErrandSpecifications.departmentIdIs(master.getDepartment().getId()));

    return errandService.findAll(spec, page - 1, 5);
  }


  @Override
  public ResponseEntity<?> findById(Long id) {
    return ResponseEntity.ok(errandService.findErrandById(id));
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

  @Override
  public ResponseEntity<?> updateErrandStatus(Long errandId, String status) {
    var errand = errandService.findErrandById(errandId);
    errand.setStatusType(status);
    errandService.updateErrands(errand);
    var responseMsg = "Email alert sent";
    emailAlertWhenErrandUpdated(errand);
    return ResponseEntity.ok(responseMsg);
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

	@Override
  public List<ErrandMatterDto> getErrandMatters() {
    return getMatters();
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
   * @see EmailService
   * @param newErrandDto
   * @param name
   * @author owpk
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
   * @see EmailService
   * @param errand
   * @author owpk
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
}
