/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.geekbrains.javacommand.command.services.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.dtos.FileDto;
import ru.geekbrains.javacommand.command.entities.File;
import ru.geekbrains.javacommand.command.repositories.FileRepository;
import ru.geekbrains.javacommand.command.services.FileService;
import ru.geekbrains.javacommand.command.util.FileDtoMapper;

@Service
public class FileServiceImpl implements FileService {

  @Autowired private FileRepository fileRepository;
  @Autowired private FileDtoMapper fileDtoMapper;

	@Override
	public List<File> uploadFiles(List<FileDto> filesDtosList) {
		List<File> filesList = fileDtoMapper.convertToFilesList(filesDtosList);
		for (File file : filesList) {
			fileRepository.save(file);
		}
		return filesList;
	}

	@Override
  public List<FileDto> listFiles(List<Long> errandsIdsList) {
    List<FileDto> filesDtosList = new ArrayList<>();
    if (errandsIdsList != null && !errandsIdsList.isEmpty()) {
      filesDtosList =
          fileDtoMapper.convertToFilesDtosList(fileRepository.getFilesByIdsList(errandsIdsList));
    } else {
      filesDtosList =
					fileDtoMapper.convertToFilesDtosList(fileRepository.getAllFiles());
    }
    return filesDtosList;
  }
}
