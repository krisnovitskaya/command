package ru.geekbrains.javacommand.command.dtos;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.OffsetDateTime;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NewErrandDto {
   @JsonProperty("status_id")
   private Long statusId;
   @JsonProperty("employee_id")
   private Long employeeId;
   @JsonProperty("date_start")
   private OffsetDateTime dateStart;
   @JsonProperty("date_end")
   private OffsetDateTime dateEnd;
   @JsonProperty("matter_id")
   private Long matterId;
   @JsonProperty("place_id")
   private Long placeId;
   @JsonProperty("comment")
   private String comment;
   @JsonProperty("created_by_id")
   private Long createdById;
   @JsonProperty("confirm_or_rejected_by")
   private Long confirmedOrRejectedBy;
}

