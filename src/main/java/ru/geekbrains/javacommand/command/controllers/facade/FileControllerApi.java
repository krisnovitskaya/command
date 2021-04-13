/*
 * License Headers.
 */
package ru.geekbrains.javacommand.command.controllers.facade;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.javacommand.command.dtos.FileDto;

@RequestMapping("/api/v1/files")
public interface FileControllerApi {

	/**
	 *
	 * @param filesDtosList
	 * @return ResponseEntity
	 */
	@PostMapping(
      path = "/upload",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<?> uploadFiles(@RequestBody (required = false) List<FileDto> filesDtosList);

	/**
	 *
	 * @param filesDtosList
	 * @return ResponseEntity
	 */
	@PutMapping(
      path = "/update",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<?> updateFiles(@RequestBody List<FileDto> filesDtosList);

	/**
	 *
	 * @param idsList
	 * @return ResponseEntity
	 */
	@PostMapping(
			path = "/download",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  ResponseEntity<?> downloadFiles(@RequestBody List<Long> idsList);

	/**
	 *
	 * @param idsList
	 * @return ResponseEntity
	 */
	@DeleteMapping(
			path = "/delete",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<?> deleteFiles(@RequestBody List<Long> idsList);

	/**
	 *
	 * @param idsList
	 * @return ResponseEntity
	 */
	@DeleteMapping(
			path = "/remove",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<?> removeFiles(@RequestBody List<Long> idsList);

	/**
	 *
	 * @param errandsIdsList
	 * @return ResponseEntity
	 */
	@PostMapping(
			path = "/listfiles",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<?> listFiles(@RequestBody (required = false) List<Long> errandsIdsList);
}