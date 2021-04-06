package ru.geekbrains.javacommand.command.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.javacommand.command.entities.Place;
import ru.geekbrains.javacommand.command.entities.PlaceType;

import java.util.Optional;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("place_type_id")
    private PlaceType placeType;

    public PlaceDto(Place place){
        this.id = place.getId();
        this.title = place.getTitle();
        this.placeType = place.getPlaceType();
    }

    public PlaceDto(Optional<Place> place){
        this.id = place.get().getId();
        this.title = place.get().getTitle();
        this.placeType = place.get().getPlaceType();
    }


}
