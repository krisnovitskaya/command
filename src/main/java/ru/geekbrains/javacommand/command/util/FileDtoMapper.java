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
import ru.geekbrains.javacommand.command.entities.File;

/** @author Igor Popovich, email: popovichia@gmail.com */
@Component
public class FileDtoMapper {

  @Autowired private ModelMapper modelMapper;

  public List<FileDto> convertToFilesDtosList(List<File> filesList) {
    List<FileDto> filesDtosList = new ArrayList<>();
		for (File file : filesList) {
      FileDto fileDto = modelMapper.map(file, FileDto.class);
			filesDtosList.add(fileDto);
		}
    return filesDtosList;
  }
  public List<File> convertToFilesList(List<FileDto> filesDtosList) {
		List<File> filesList = new ArrayList<>();
		for (FileDto fileDto : filesDtosList) {
			File file = modelMapper.map(fileDto, File.class);
			filesList.add(file);
		}
		return filesList;
	}
}
