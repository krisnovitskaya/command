/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.geekbrains.javacommand.command.services.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.dtos.FileDto;
import ru.geekbrains.javacommand.command.entities.FileEntity;
import ru.geekbrains.javacommand.command.repositories.FileRepository;
import ru.geekbrains.javacommand.command.services.FileService;
import ru.geekbrains.javacommand.command.util.FileDtoMapper;
import ru.geekbrains.javacommand.command.util.FileUtilities;

@Service
public class FileServiceImpl implements FileService {

  @Autowired private FileRepository fileRepository;
  @Autowired private FileDtoMapper fileDtoMapper;
	@Autowired private FileUtilities fileUtilities;
	
	@Override
	public List<FileEntity> uploadFiles(List<FileDto> filesDtosList) {
		List<FileEntity> filesList = new ArrayList<>();
			if (filesDtosList != null && !filesDtosList.isEmpty()) {
				filesList = fileDtoMapper.convertToFilesList(filesDtosList);
				for (FileEntity file : filesList) {
					fileRepository.save(file);
				}
			}
		return filesList;
	}

	@Override
	public File getFile(Long id) {
		File resultFile = null;
		try {
			resultFile = fileUtilities.getFileFromDB(fileRepository.getFileById(id));
		} catch (IOException ex) {
			Logger.getLogger(FileServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
		return resultFile;
	}

	@Override
	public File getFiles(List<Long> idsList) {
		return null;
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
