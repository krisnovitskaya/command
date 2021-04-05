package ru.geekbrains.javacommand.command.dtos;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.geekbrains.javacommand.command.entities.Place;

@Getter
@Setter
@NoArgsConstructor
@JsonAutoDetect
public class PlaceDto {
    private Long id;
    private String title;
    private String type;

    public PlaceDto(Place p) {
        this.id = p.getId();
        this.title = p.getTitle();
        this.type = p.getPlaceType() != null ? p.getPlaceType().getType() : "def";
    }
}
