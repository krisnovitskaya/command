package ru.geekbrains.javacommand.command.exceptions;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@JsonAutoDetect
@Data
@NoArgsConstructor
public class AuthError {
   private Integer status;
   private String message;
   private Date timeStamp;

   public AuthError(Integer status, String message) {
      this.status = status;
      this.message = message;
      timeStamp = new Date();
   }
}
