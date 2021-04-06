/*
 * License Headers.
 */

package ru.geekbrains.javacommand.command.dtos;

import java.time.OffsetDateTime;
import lombok.Data;

/** @author Igor Popovich, email: popovichia@gmail.com */
@Data
public class ErrandUpdateDto extends ErrandCreateDto {

  private Long id;
  private OffsetDateTime updated;

  public ErrandUpdateDto(
      Long id,
      Long statusId,
      Long employeeId,
      ErrandDetailsDto errandDetailsDto,
      OffsetDateTime dateStart,
      OffsetDateTime dateEnd,
			OffsetDateTime updated) {
    super(statusId, employeeId, dateStart, dateEnd, errandDetailsDto);
    this.id = id;
		this.updated = updated;
  }
}
