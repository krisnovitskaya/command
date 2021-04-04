/*
 * License Headers.
 */

package ru.geekbrains.javacommand.command.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

/** @author Igor Popovich, email: popovichia@gmail.com */
@Data
@AllArgsConstructor
public class ErrandDeleteDto {

  private Long id;
  private int deleted;
	
}
