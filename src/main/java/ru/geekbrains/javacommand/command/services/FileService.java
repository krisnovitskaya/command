/*
 * License Headers.
 */
package ru.geekbrains.javacommand.command.services;

import java.io.File;
import java.util.List;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.dtos.FileDto;
import ru.geekbrains.javacommand.command.entities.FileEntity;

@Service
public interface FileService {
	
	List<FileEntity> uploadFiles(List<FileDto> filesDtosList);
	File downloadFile(Long id);
	File downloadFiles(List<Long> idsList);
	List<FileDto> listFiles(List<Long> errandsIdsList);
}
