package ru.geekbrains.javacommand.command.dtos;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.geekbrains.javacommand.command.entities.Place;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.javacommand.command.entities.Place;
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
    
		private String type;

		@JsonProperty("place_type_id")
    private PlaceType placeType;

    public PlaceDto(Place p) {
        this.id = p.getId();
        this.title = p.getTitle();
        this.type = p.getPlaceType() != null ? p.getPlaceType().getType() : "def";
        this.placeType = p.getPlaceType();
    }

		public PlaceDto(Optional<Place> place){
        this.id = place.get().getId();
        this.title = place.get().getTitle();
        this.placeType = place.get().getPlaceType();
    }
}
