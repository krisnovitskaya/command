/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.geekbrains.javacommand.command.util;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.geekbrains.javacommand.command.dtos.FileDto;
import ru.geekbrains.javacommand.command.entities.FileEntity;

/** @author Igor Popovich, email: popovichia@gmail.com */
@Component
public class FileDtoMapper {

  @Autowired private ModelMapper modelMapper;

  public List<FileDto> convertToFilesDtosList(List<FileEntity> filesList) {
    List<FileDto> filesDtosList = new ArrayList<>();
		for (FileEntity file : filesList) {
      FileDto fileDto = modelMapper.map(file, FileDto.class);
			filesDtosList.add(fileDto);
		}
    return filesDtosList;
  }
  public List<FileEntity> convertToFilesList(List<FileDto> filesDtosList) {
		List<FileEntity> filesList = new ArrayList<>();
		for (FileDto fileDto : filesDtosList) {
			FileEntity file = modelMapper.map(fileDto, FileEntity.class);
			filesList.add(file);
		}
		return filesList;
	}
}
