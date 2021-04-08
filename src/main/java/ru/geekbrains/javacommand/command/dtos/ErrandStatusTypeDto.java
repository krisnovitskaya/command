package ru.geekbrains.javacommand.command.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.javacommand.command.entities.ErrandStatusType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrandStatusTypeDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("status")
    private String status;

    public ErrandStatusTypeDto(ErrandStatusType errandStatusType) {
        this.id = errandStatusType.getId();
        this.status = errandStatusType.getStatus();
    }

}
