package ru.geekbrains.javacommand.command.dtos;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.NoArgsConstructor;
import ru.geekbrains.javacommand.command.entities.Place;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.geekbrains.javacommand.command.entities.PlaceType;
import java.util.Optional;

@Data
@NoArgsConstructor
@JsonAutoDetect
public class PlaceDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    private String title;

    public PlaceDto(Place p) {
        this.id = p.getId();
        this.title = p.getTitle();
    }
}
