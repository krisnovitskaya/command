/*
 * License Headers.
 */

package ru.geekbrains.javacommand.command.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

/** @author Igor Popovich, email: popovichia@gmail.com */
@Data
@AllArgsConstructor
public class ErrandAboutInfoDto {

  private String statusType;
  private String employeeFirstName;
  private String employeeMiddleName;
  private String employeeLastName;
  private String employeePosition;
  private String employeeUserName;
  private String departmentTitle;
  private String departmentMasterFirstName;
  private String departmentMasterMiddleName;
  private String departmentMasterLastName;
  private String departmentMasterUserName;
  private String detailMatterType;
  private String detailPlace;
  private String detailPlaceType;
  private String detailComment;
  private String detailCreatedByFirstName;
  private String detailCreatedByMiddleName;
  private String detailCreatedByLastName;
  private String detailConfirmedOrRejectedByFirstName;
  private String detailConfirmedOrRejectedByMiddleName;
  private String detailConfirmedOrRejectedByLastname;

}
