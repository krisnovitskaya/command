package ru.geekbrains.javacommand.command.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.javacommand.command.entities.Position;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("position")
    private String position;

    public PositionDto(Position position) {
        this.id = position.getId();
        this.position = position.getPosition();
    }

}
