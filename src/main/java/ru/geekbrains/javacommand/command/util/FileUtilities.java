/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.geekbrains.javacommand.command.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.geekbrains.javacommand.command.entities.File;

/** @author Igor Popovich, email: popovichia@gmail.com */
@Component
public class FileUtilities {

  @Value("tmpdirpath")
  private String tmpDirPath;

  public java.io.File createArchive(List<File> filesList)
      throws FileNotFoundException, IOException {

    java.io.File tmpDir = new java.io.File(tmpDirPath);
    if (checkDir(tmpDir)) {
      for (File file : filesList) {
        java.io.File tmpFile =
            new java.io.File(tmpDirPath + java.io.File.separator + file.getFileName());
        FileOutputStream fileOutputStream = new FileOutputStream(tmpFile);
        fileOutputStream.write(file.getFileData());
      }
    }
    return null;
  }

  public boolean checkDir(java.io.File tmpDirPath) {

    boolean result = true;
    if (!tmpDirPath.exists() || !tmpDirPath.isDirectory()) {
      tmpDirPath.mkdir();
    }
    return result;
  }
}
