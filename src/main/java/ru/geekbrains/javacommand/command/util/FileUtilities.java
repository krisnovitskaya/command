/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.geekbrains.javacommand.command.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.geekbrains.javacommand.command.entities.FileEntity;
import org.apache.commons.codec.binary.Base64;

/** @author Igor Popovich, email: popovichia@gmail.com */
@Component
public class FileUtilities {

  @Value("${tmpdirpath}")
  private String tmpDirPath;

	public File getFileFromDB(FileEntity fileEntity) throws FileNotFoundException, IOException {
		File resultFile = null;
		if (checkDir(tmpDirPath)) {
			resultFile = new File(tmpDirPath + File.separator + fileEntity.getFileName());
			try (FileOutputStream fileOutputStream = new FileOutputStream(resultFile)) {
				String dataUrl = fileEntity.getFileData();
				Logger.getLogger(FileUtilities.class.getName()).log(Level.SEVERE, dataUrl);
				int dataStartIndex = dataUrl.indexOf(",") + 1;
				final String data = dataUrl.substring(dataStartIndex);
				fileOutputStream.write(Base64.decodeBase64(data));
				fileOutputStream.flush();
			}
		}
		return resultFile;
	}
	
  public File createArchive(List<FileEntity> filesEntitiesList)
      throws FileNotFoundException, IOException {

    if (checkDir(tmpDirPath)) {
      for (FileEntity fileEntity : filesEntitiesList) {
        File tmpFile = new File(tmpDirPath + File.separator + fileEntity.getFileName());
        FileOutputStream fileOutputStream = new FileOutputStream(tmpFile);
        fileOutputStream.write(fileEntity.getFileData().getBytes());
      }
    }
    return null;
  }

  public boolean checkDir(String tmpDirPath) {

    boolean result = true;
    File tmpDir = new File(tmpDirPath);
    if (!tmpDir.exists() || !tmpDir.isDirectory()) {
      tmpDir.mkdir();
    }
    return result;
  }
}
