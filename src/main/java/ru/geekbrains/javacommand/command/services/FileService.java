/*
 * License Headers.
 */
package ru.geekbrains.javacommand.command.services;

import java.util.List;
import org.springframework.stereotype.Service;
import ru.geekbrains.javacommand.command.dtos.FileDto;
import ru.geekbrains.javacommand.command.entities.File;

@Service
public interface FileService {
	
	List<File> uploadFiles(List<FileDto> filesDtosList);
	List<FileDto> listFiles(List<Long> errandsIdsList);
}
