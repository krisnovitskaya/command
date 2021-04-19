package ru.geekbrains.javacommand.command.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileDto {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("fileName")
  private String fileName;

  @JsonProperty("fileData")
  private String fileData;
	
  @JsonProperty("authorId")
  private Long authorId;

	@JsonProperty("errandId")
  private Long errandId;

	@JsonProperty("deleted")
  private boolean deleted;
}
