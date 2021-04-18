/*
 * License Headers.
 */

package ru.geekbrains.javacommand.command.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
	public ResponseEntity<?> downloadFile(Long id) {
		File f = fileService.getFile(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("FileName", f.getName());
		try {
			return new ResponseEntity(FileUtils.readFileToByteArray(f), headers, HttpStatus.OK);
		} catch (IOException ioException) {
			Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ioException);
		}
		return null;
	}

	@Override
	public ResponseEntity<?> downloadFiles(List<Long> idsList) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public ResponseEntity<?> deleteFile(Long id) {
		fileService.deleteFile(id);
		return ResponseEntity.ok().build();
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
