/*
 * License Headers.
 */

package ru.geekbrains.javacommand.command.dtos;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

/** @author Igor Popovich, email: popovichia@gmail.com */
@Data
@AllArgsConstructor
public class ErrandCreateDto {

  private Long statusId;
  private Long employeeId;
  private OffsetDateTime dateStart;
  private OffsetDateTime dateEnd;
  private ErrandDetailsDto errandDetailsDto;
}
