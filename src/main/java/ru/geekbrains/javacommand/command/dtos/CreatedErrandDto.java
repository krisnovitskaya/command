package ru.geekbrains.javacommand.command.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.javacommand.command.entities.ErrandDetails;
import java.time.OffsetDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatedErrandDto {

        private String statusType;
        private Long employeeId;
        private OffsetDateTime dateStart;
        private OffsetDateTime dateEnd;
        private Long matterId;
        private Long placeId;
        private String comment;
        private Long createdById;
        private Long confirmedOrRejectedBy;
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        private OffsetDateTime created;
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        private OffsetDateTime updated;
        private boolean deleted;
        private Long confirmedOrRejectedById;
        private ErrandDetails errandDetails;

}
