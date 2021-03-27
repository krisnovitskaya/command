/*
 * License Headers.
 */

package ru.geekbrains.javacommand.command.dtos;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;

/** @author Igor Popovich, email: popovichia@gmail.com */
@Data
@AllArgsConstructor
public class ErrandResponseDto {
	
	private Long errandId;
	private String errandStatusType;
	private String errandEmployeeName;
	private String errandEmployeeMiddleName;
	private String errandEmployeeSurname;
	private String errandEmployeePosition;
	private String errandEmployeeDepartment;
	private String errandEmployeeDepartmentMaster;
	private String errandEmployeeUserName;
	private String errandDetailMatterType;
	private String errandDetailPlace;
	private String errandDetailPlaceType;
	private String errandDetailComment;
	private String errandDetailCreatedBy;
	private String errandDetailConfirmedOrRejectedBy;
	private Timestamp errandDateStart;
	private Timestamp errandDateEnd;
	
}
