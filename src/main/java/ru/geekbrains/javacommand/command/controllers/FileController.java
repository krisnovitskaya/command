/*
 * License Headers.
 */

package ru.geekbrains.javacommand.command.controllers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.javacommand.command.controllers.facade.FileControllerApi;
import ru.geekbrains.javacommand.command.dtos.FileDto;
import ru.geekbrains.javacommand.command.services.FileService;

@RestController
@RequiredArgsConstructor
public class FileController implements FileControllerApi {
	
	@Autowired
	private FileService fileService;

	@Override
	public ResponseEntity<?> uploadFiles(List<FileDto> filesDtosList) {
    return ResponseEntity.ok(fileService.uploadFiles(filesDtosList));
	}

	@Override
	public ResponseEntity<?> updateFiles(List<FileDto> filesDtosList) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public ResponseEntity<?> downloadFiles(List<Long> idsList) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public ResponseEntity<?> deleteFiles(List<Long> idsList) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public ResponseEntity<?> removeFiles(List<Long> idsList) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public ResponseEntity<?> listFiles(List<Long> errandsIdsList) {
    return ResponseEntity.ok(fileService.listFiles(errandsIdsList));
	}
}
