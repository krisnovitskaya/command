package ru.geekbrains.javacommand.command.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.javacommand.command.entities.PlaceType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceTypeDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("type")
    private String type;

    public PlaceTypeDto(PlaceType placeType){
        this.id = placeType.getId();
        this.type = placeType.getType();
    }

}
