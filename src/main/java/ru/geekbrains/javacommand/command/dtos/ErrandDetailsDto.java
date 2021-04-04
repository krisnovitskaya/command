/*
 * License Headers.
 */

package ru.geekbrains.javacommand.command.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

/** @author Igor Popovich, email: popovichia@gmail.com */
@Data
@AllArgsConstructor
public class ErrandDetailsDto {

  private Long errandMatterTypeId;
  private Long placeId;
  private String comment;
  private Long createdById;
  private Long confirmedOrRejectedById;
}
